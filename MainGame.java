import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainGame {

    public static void main(String[] args) {

        List<Integer> diceSides = List.of(1, 2, 3, 4, 5, 6);
        ArrayList<Combination> combinations = new ArrayList<>(Combination.createCombinations());
        Scanner sc = new Scanner(System.in);

        System.out.print("\nWelcome to the Yahtzee Game!\n\n" +
                         "Please enter you name: ");
        String name = sc.nextLine();

        Player user = new Player(name, combinations);
        Player systemPlayer = new Player("system", combinations);

    }

}
