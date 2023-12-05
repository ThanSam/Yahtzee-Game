import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LowerSectionCombination extends Combination{

    private List<Integer> pointsPatterns;
    public LowerSectionCombination(String name, int pointsGained, List<Integer> pointsPatterns) {
        super(name, pointsGained);
        this.pointsPatterns = new ArrayList<>(pointsPatterns);
    }

    @Override
    public boolean isFormed(List<Integer> dice) {
        switch (name) {
            case "Three of a kind", "Four of a kind", "Full House", "YAHTZEE": {

                Map<Integer, Long> counting = dice.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                int conditions[] = new int[pointsPatterns.size()];
                Arrays.fill(conditions, 0);

                for (long v : counting.values())
                    for (int i = 0; i < pointsPatterns.size(); i++)
                        if (v >= pointsPatterns.get(i)) {
                            conditions[i] = 1;
                            break;
                        }

                return Arrays.stream(conditions).allMatch(s -> s == 1);
            }
            case "Small straight": {

                if (dice.equals(Arrays.asList(1, 2, 3, 4)) || dice.equals(Arrays.asList(2, 3, 4, 5)) || dice.equals(Arrays.asList(3, 4, 5, 6)))
                    return true;
            }
            case "Large straight": {
                if (dice.equals(Arrays.asList(1, 2, 3, 4, 5)) || dice.equals(Arrays.asList(2, 3, 4, 5, 6)))
                    return true;

            }
            default: return false;
        }
    }

    @Override
    public int getPoints(List<Integer> dice) {
        return switch (name) {
            case "Three of a kind", "Four of a kind", "Chance" -> dice.stream().mapToInt(Integer::intValue).sum();
            default -> 0;
        };
    }
}
