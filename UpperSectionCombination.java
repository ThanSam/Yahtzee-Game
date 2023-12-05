import java.util.List;

public class UpperSectionCombination extends Combination {

    private int combinationPattern;

    public UpperSectionCombination(String name, int pointsGained, int combinationPattern) {
        super(name, pointsGained);
        this.combinationPattern = combinationPattern;
    }

    @Override
    public boolean isFormed(List<Integer> dice) {
        return dice.contains(combinationPattern);
    }

    @Override
    public int getPoints(List<Integer> dice) {
        return (int) dice.stream().filter(i -> i.equals(combinationPattern)).count();
    }
}
