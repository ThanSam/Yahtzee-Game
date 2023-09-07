import java.util.*;
import java.util.stream.Collectors;


public class MainGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nWelcome to the Yahtzee Game!\n\n" +
                         "Please enter your name: ");
        String name = sc.nextLine();
        System.out.println();

        ArrayList<Combination> combinations = new ArrayList<>(Combination.createCombinations());
        Player user = new Player(name, combinations);
        Player systemPlayer = new Player("System", combinations);

        List<Player> players = List.of(user, systemPlayer);

        Random random = new Random();
        List<Integer> dices;
        int turn = 0;

        while(true) {

            dices = new Random().ints(5, 1, 7).boxed().collect(Collectors.toList());

            System.out.print("\n" + players.get(turn).getName() +  " turn: ");
            for(int dice : dices)
                System.out.print(dice + " ");
            System.out.println("\n");

            List<Combination> availableCombinations = players.get(turn).getAvailableCombinations();

            System.out.println("-------FORMED COMBINATIONS-------\n");
            for(Combination combination : availableCombinations) {
                    if(combination.isFormed(dices))
                        System.out.format("\t%s\t\tPoints: %d\n", combination.getCombinationName(), combination.getPoints(dices));
            }

            System.out.print("\nChoose a formed combination or the dice(1-6) you want to roll again: ");
            List<String> input = Arrays.stream(sc.nextLine().split(" ")).toList();


            if (combinations.stream().map(c -> c.getCombinationName()).anyMatch(c -> c.equals(input.get(0)))) {
                Combination combinationFormed = Combination.findCombinationByName(input.get(0));
                players.get(turn).addPoints(combinationFormed.getPoints(dices));
                players.get(turn).removeAvailableCombination(combinationFormed);
                if (combinationFormed.getCombinationType().equals("UPPER_SECTION"))
                    players.get(turn).checkForBonus(combinationFormed.getPoints(dices));
            }
            else {  //Roll some dice again

            }

            System.out.println("\nSCORE: " + players.get(0).getName() + " " + Integer.toString(players.get(0).getScore()) + " - "
                                           + Integer.toString(players.get(1).getScore()) + " " + players.get(1).getName());
            turn = (turn + 1) % 2;

       }
    }

}
