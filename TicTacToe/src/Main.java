import java.util.*;

public class Main {

    static int dimension;
    static final int MAX_DIMENSION = 8;
    static final int MIN_DIMENSION = 2;
    static final String PLAYER_S = " S "; // 0
    static final String PLAYER_O = " O "; // 1
    static String currentPlayer = "";
    static int player;// if player is 0 pc will play. if player is 1 person will play
    static int playerScore = 0;
    static int computerScore = 0;

    public static void main(String[] args) {

        Random random = new Random();

        Scanner scanner = new Scanner(System.in);

        System.out.println("İstediğiniz kare uzunluğunu giriniz(3-7)");

        dimension = Integer.parseInt(scanner.nextLine());

        while (!(dimension< MAX_DIMENSION && dimension > MIN_DIMENSION)){
            System.out.println("Lütfen 3 ile 7 arasında bir sayı giriniz!!!");
            dimension = Integer.parseInt(scanner.nextLine());
        }

        //randomly selected player
        if (random.nextInt(2)==0) {
            currentPlayer = PLAYER_S;
        }
        else
            currentPlayer = PLAYER_O;

        player = random.nextInt(2); // if player is 0 pc will play. if player is 1 person will play

        Board board = new Board(dimension);
        board.initializeBoard();
        board.printBoard();

        if (player == 0){
            System.out.println("Bilgisayar ilk olarak oynayacak. Karakteri :" + currentPlayer);
        }else
            System.out.println("İlk sen oynayacaksın karakterin :" + currentPlayer);



        while (!board.isGameOver()){
           //pc playing
            if (player == 0){
                int[] rowColumn = board.makePcMove(currentPlayer);
                System.out.println(board.printBoard());
                System.out.println("Bilgisayar Oynadı");
                if(board.isScore(rowColumn[0],rowColumn[1],currentPlayer))
                    computerScore +=1;
                printScores();

            }else {
                System.out.println("Satır sayısı giriniz : ");
                int row = Integer.parseInt(scanner.nextLine());
                System.out.println("Sütün sayısı giriniz : ");
                int column = Integer.parseInt(scanner.nextLine());
                while(!board.makeMove(row,column,currentPlayer)){
                    System.out.println("Satır sayısı giriniz : ");
                    row = Integer.parseInt(scanner.nextLine());
                    System.out.println("Sütün sayısı giriniz : ");
                    column = Integer.parseInt(scanner.nextLine());
                }

                System.out.println(board.printBoard());
                if (board.isScore(row,column,currentPlayer))
                    playerScore +=1;
                printScores();
            }

            if (Objects.equals(currentPlayer, PLAYER_O))
                currentPlayer = PLAYER_S;
            else if (Objects.equals(currentPlayer, PLAYER_S))
                currentPlayer = PLAYER_O;
            if (player == 0)
                player = 1;
            else if (player == 1)
                player = 0;


        }




    }

    public static void printScores(){
        System.out.println("Puan Durumu : ");
        System.out.println("Sen : " + playerScore);
        System.out.println("Bilgisayar : " + computerScore);

    }
}