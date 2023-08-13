import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainGame {

    public static void main(String[] args) {

        List<Integer> dice = List.of(1, 2, 3, 4, 5, 6);
        Scanner sc = new Scanner(System.in);

        System.out.print("\nWelcome to the Yahtzee Game!\n\n" +
                "Please enter you name: ");
        String name = sc.nextLine();

        Player user = new Player(name);
        Player systemPlayer = new Player("system");

        //Test
        Combination ones = new Combination("Ones", Combination.CombinationType.UPPER_SECTION, 1, Arrays.asList(1));
        System.out.println(ones.IsFormed(dice));

    }

}
