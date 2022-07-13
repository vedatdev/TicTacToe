import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Board {

    private int row;
    private int column;
    private String[][] boardElements;
    private List<Cell> availableCells = new ArrayList<>();
    private static final String THREE_SPACES = "   ";
    private static final String S_CHAR = " S ";
    private static final String O_CHAR = " O ";

    public Board(int rowColumnNumber){
        this.row = rowColumnNumber;
        this.column = rowColumnNumber;
        this.boardElements = new String[row][column];
    }


    /**
     * initialized board
     */
    public void initializeBoard(){
        for (int i = 0; i<row; i++){
            for (int j = 0; j<column; j++){
                availableCells.add(new Cell(i,j));
                boardElements[i][j] = "   ";
            }
        }
    }

    /**
     * printing board
     * @return strBoard
     */
    public String printBoard(){
        String strBoard = "";
        for (int i = 0; i<row; i++){
            for (int j = 0; j<column; j++){
                if (j<column-1)
                    strBoard += boardElements[i][j] + "|";
                else
                    strBoard += boardElements[i][j];
            }
            if (i != column-1) {
                strBoard += "\n";
                for (int k = 0; k < row; k++) {
                    strBoard += "---+";
                }
                strBoard += "\n";
            }
        }
        return strBoard;
    }

    /**
     *
     * @param i coordinate
     * @param j coordinate
     * @param player S or O
     * @return move is happened or not
     */
    public boolean makeMove(int i, int j, String player){
        if (Objects.equals(this.boardElements[i][j], THREE_SPACES)) {
            boardElements[i][j] = player;
            availableCells.remove(new Cell(i, j));
            return true;
        }else {
            System.out.println("Bu hücre dolu lütfen başka bir hücre seçiniz!");
            return false;
        }
    }

    /**
     *
     * @param lastRow entered row
     * @param lastColumn entered column
     * @param lastChar S or O
     * @return
     */
    public boolean isScore(int lastRow, int lastColumn,String lastChar){
        if (Objects.equals(lastChar, S_CHAR)){
            //checking to the down
            if (lastRow + 2 < row){
                if (Objects.equals(boardElements[lastRow + 1][lastColumn], O_CHAR) && Objects.equals(boardElements[lastRow + 2][lastColumn], S_CHAR))
                    return true;
            }
            //checking diagonal right and up
            if (lastRow + 2 < row && lastColumn + 2 < column)
                if(Objects.equals(boardElements[lastRow + 1][lastColumn + 1],O_CHAR) && Objects.equals(boardElements[lastRow + 2][lastColumn + 2],S_CHAR))
                    return true;
            //checking to the up
            if (lastRow - 2 >= 0)
                if (Objects.equals(boardElements[lastRow - 1][lastColumn], O_CHAR) && Objects.equals(boardElements[lastRow - 2][lastColumn], S_CHAR))
                    return true;
            //checking to the right
            if (lastColumn + 2 < column)
                if (Objects.equals(boardElements[lastRow][lastColumn + 1],O_CHAR) && Objects.equals(boardElements[lastRow][lastColumn + 2],S_CHAR))
                    return true;
            //checking to the left
            if (lastColumn - 2 >= 0)
                if (Objects.equals(boardElements[lastRow][lastColumn - 1],O_CHAR) && Objects.equals(boardElements[lastRow][lastColumn - 2],S_CHAR))
                    return true;
            //checking diagonal left and up
            if (lastRow - 2 >= 0 && lastColumn - 2 >= 0)
                if (Objects.equals(boardElements[lastRow - 1][lastColumn - 1],O_CHAR) && Objects.equals(boardElements[lastRow - 2][lastColumn - 2],S_CHAR))
                    return true;
            //checking diagonal right and down
            if (lastRow - 2 >= 0 && lastColumn + 2 < column)
                if (Objects.equals(boardElements[lastRow - 1][lastColumn + 1],O_CHAR) && Objects.equals(boardElements[lastRow - 2][lastColumn + 2],S_CHAR))
                    return true;
            //checking diagonal left and down
            if(lastRow + 2 < row && lastColumn - 2 >= 0)
                if (Objects.equals(boardElements[lastRow + 1][lastColumn - 1],O_CHAR) && Objects.equals(boardElements[lastRow + 2][lastColumn - 2],S_CHAR))
                    return true;
        } else if (Objects.equals(lastChar, O_CHAR)) {
            //if lastChar is "O" we have to look for a one char on direction and one char on opposite direction
            //checking up and down
            if (lastRow - 1 >= 0 && lastRow + 1 < row)
                if (Objects.equals(boardElements[lastRow - 1][lastColumn],S_CHAR) && Objects.equals(boardElements[lastRow + 1],S_CHAR))
                    return true;
            //checking left and right
            if (lastColumn + 1 < column && lastColumn - 1 >= 0)
                if (Objects.equals(boardElements[lastRow][lastColumn - 1],S_CHAR) && Objects.equals(boardElements[lastRow][lastColumn + 1],S_CHAR))
                    return true;
            if (lastRow + 1 < row && lastColumn + 1 < column && lastColumn - 1 >= 0 && lastRow - 1 >= 0)
                if (Objects.equals(boardElements[lastRow - 1][lastColumn - 1],S_CHAR) && Objects.equals(boardElements[lastRow + 1][lastColumn + 1],S_CHAR))
                    return true;
                else if (Objects.equals(boardElements[lastRow - 1][lastColumn + 1],S_CHAR) && Objects.equals(boardElements[lastRow - 1][lastColumn + 1],S_CHAR))
                    return true;
        }
        return false;
    }
    public List<Cell> getAvailableCells(){
        return availableCells;
    }

    public int[] makePcMove(String player){
        Random random = new Random();

        Cell cell = availableCells.get(random.nextInt(availableCells.size()));

        makeMove(cell.getRow(),cell.getColumn(),player);

        availableCells.remove(cell);

        return new int[]{cell.row, cell.column};

    }

    public boolean isGameOver() {
        return availableCells.size()==0;
    }
}
