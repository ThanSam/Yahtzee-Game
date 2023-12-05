import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Combination {

    protected String name;
    protected int pointsGained;

    public Combination(String name, int pointsGained) {
        this.name = name;
        this.pointsGained = pointsGained;
    }

    public String getCombinationName() {
        return name;
    }

    public static Combination findCombinationByName(String name) {
        for (Combination c : createCombinations())
            if (c.getCombinationName().equals(name))
                return c;

        return createCombinations().get(0);
    }

    public abstract boolean isFormed(List<Integer> dice);

    public abstract int getPoints(List<Integer> dice);


    public static ArrayList<Combination> createCombinations() {

        ArrayList<Combination> combinations = new ArrayList<>();

        //Upper Section Combinations
        combinations.add(new UpperSectionCombination("Ones",  1, 1));
        combinations.add(new UpperSectionCombination("Twos", 2, 2));
        combinations.add(new UpperSectionCombination("Threes", 3, 3));
        combinations.add(new UpperSectionCombination("Fours", 4, 4));
        combinations.add(new UpperSectionCombination("Fives", 5, 5));
        combinations.add(new UpperSectionCombination("Sixes", 6, 6));

        //Lower Section Combinations
        combinations.add(new LowerSectionCombination("Three of a kind", 1, List.of(3)));
        combinations.add(new LowerSectionCombination("Four of a kind", 1, List.of(4)));
        combinations.add(new LowerSectionCombination("Full House", 25, List.of(3,2)));
        combinations.add(new LowerSectionCombination("Small straight",30,List.of()));
        combinations.add(new LowerSectionCombination("Large straight", 40,List.of()));
        combinations.add(new LowerSectionCombination("Chance", 1,List.of()));

        combinations.add(new LowerSectionCombination("YAHTZEE", 50, List.of(5)));

        return combinations;
    }
}
