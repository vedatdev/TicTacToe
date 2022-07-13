import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("asd");
        list.add("pofdkmg");
        list.remove("asd");
        list.remove("pofdkmg");

        System.out.println(list.size());

    }


}
