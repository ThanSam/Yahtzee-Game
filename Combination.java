import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Combination {

    public enum CombinationType {
        UPPER_SECTION, LOWER_SECTION
    }
    private String name;
    private CombinationType combinationType;
    private int pointsGained;

    private List<Integer> PointsPatterns;

    public Combination(String name, CombinationType combinationType, int pointsGained, List<Integer> PointsPatterns) {
        this.name = name;
        this.combinationType = combinationType;
        this.pointsGained = pointsGained;
        this.PointsPatterns = PointsPatterns;
    }

    public String getCombinationName() {
        return name;
    }

    public CombinationType getCombinationType() {
        return combinationType;
    }

    public static Combination findCombinationByName(String name) {
        for (Combination c : createCombinations())
            if (c.getCombinationName().equals(name))
                return c;

        return createCombinations().get(0);
    }

    public boolean isFormed(List<Integer> dice) {

        if (combinationType == CombinationType.UPPER_SECTION) {
            if (dice.contains(PointsPatterns.get(0)))
                return true;
        }
        else {      //for lower section combinations

            switch (name) {
                case "Three of a kind", "Four of a kind", "Full House", "YAHTZEE":
                {
                    Map<Integer, Long> counting = dice.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                    int conditions[] = new int[PointsPatterns.size()];
                    Arrays.fill(conditions, 0);

                    for (long v: counting.values())
                        for(int i=0;i<PointsPatterns.size();i++)
                            if(v>=PointsPatterns.get(i)) {
                                conditions[i] = 1;
                                break;
                            }

                    boolean conditionsMatched = Arrays.stream(conditions).allMatch(s -> s==1);
                    return conditionsMatched;
                }
                case "Small straight":
                {
                    if (dice.equals(Arrays.asList(1,2,3,4)) || dice.equals(Arrays.asList(2,3,4,5)) || dice.equals(Arrays.asList(3,4,5,6)))
                        return true;
                }
                case "Large straight":
                    if (dice.equals(Arrays.asList(1,2,3,4,5)) || dice.equals(Arrays.asList(2,3,4,5,6)))
                        return true;

            }

        }
        return false;
    }

    public int getPoints(List<Integer> dice) {

        if (combinationType == CombinationType.UPPER_SECTION) {
            int count = 0;
            for (int i = 0; i < dice.size(); i++) {
                if (dice.get(i).equals(PointsPatterns.get(0)))
                    count++;
            }
            return (count * pointsGained);
        }
        else {      //for lower section combinations

            switch (name) {
                case "Full House", "Small straight", "Large straight", "YAHTZEE":
                    return pointsGained;

                case "Three of a kind", "Four of a kind", "Chance":
                    return dice.stream().mapToInt(Integer::intValue).sum();

                default:
                    return 0;

            }
        }
    }

    public static ArrayList<Combination> createCombinations() {

        ArrayList<Combination> combinations = new ArrayList<>();

        //Upper Section Combinations
        combinations.add(new Combination("Ones", Combination.CombinationType.UPPER_SECTION, 1, Arrays.asList(1)));
        combinations.add(new Combination("Twos", Combination.CombinationType.UPPER_SECTION, 2, Arrays.asList(2)));
        combinations.add(new Combination("Threes", Combination.CombinationType.UPPER_SECTION, 3, Arrays.asList(3)));
        combinations.add(new Combination("Fours", Combination.CombinationType.UPPER_SECTION, 4, Arrays.asList(4)));
        combinations.add(new Combination("Fives", Combination.CombinationType.UPPER_SECTION, 5, Arrays.asList(5)));
        combinations.add(new Combination("Sixes", Combination.CombinationType.UPPER_SECTION, 6, Arrays.asList(6)));

        //Lower Section Combinations
        combinations.add(new Combination("Three of a kind", Combination.CombinationType.LOWER_SECTION, 1, Arrays.asList(3)));
        combinations.add(new Combination("Four of a kind", Combination.CombinationType.LOWER_SECTION, 1, Arrays.asList(4)));
        combinations.add(new Combination("Full House", Combination.CombinationType.LOWER_SECTION, 25, Arrays.asList(3,2)));
        combinations.add(new Combination("Small straight", Combination.CombinationType.LOWER_SECTION, 30, Arrays.asList()));
        combinations.add(new Combination("Large straight", Combination.CombinationType.LOWER_SECTION, 40, Arrays.asList()));
        combinations.add(new Combination("Chance", Combination.CombinationType.LOWER_SECTION, 1, Arrays.asList()));

        combinations.add(new Combination("YAHTZEE", Combination.CombinationType.LOWER_SECTION, 50, Arrays.asList(5)));

        return combinations;
    }
}
