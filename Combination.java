import java.util.List;

public class Combination {

    public enum CombinationType {
        UPPER_SECTION, LOWER_SECTION
    }
    private String name;
    private CombinationType combinationType;
    private int pointsGained;
    private List<Integer> PointsPattern;

    public Combination(String name, CombinationType combinationType, int pointsGained, List<Integer> PointsPattern) {
        this.name = name;
        this.combinationType = combinationType;
        this.pointsGained = pointsGained;
        this.PointsPattern = PointsPattern;
    }

    public void setPointsPattern(List<Integer> pointsPattern) {
        PointsPattern = pointsPattern;
    }

    public boolean IsFormed(List<Integer> dice) {
        if (combinationType == CombinationType.UPPER_SECTION)
            if(dice.contains(PointsPattern.get(0)))
                return true;
        else {
                //Collections.sort(dice);

        }

        return false;
    }

    public int getPoints(List<Integer> dice) {

        if (combinationType == CombinationType.UPPER_SECTION) {
            int count = 0;
            for (int i = 0; i < dice.size(); i++) {
                if (dice.get(i).equals(PointsPattern.get(0)))
                    count++;
            }
            return (count * pointsGained);
        }
        else {

            return 0;
        }
    }
}
