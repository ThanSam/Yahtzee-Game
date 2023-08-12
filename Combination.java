
public class Combination {

    public enum CombinationType {
        UPPER_SECTION, LOWER_SECTION
    }

    private String name;
    private CombinationType combinationType;
    private int points;

    public Combination(String name, CombinationType combinationType, int points) {
        this.name = name;
        this.combinationType = combinationType;
        this.points = points;
    }
}
