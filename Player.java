import java.util.List;

public class Player {

    private String name;
    private List<Combination> availableCombinations;
    private int score = 0;

    public Player(String name, List<Combination> availableCombinations) {
        this.name = name;
        this.availableCombinations = availableCombinations;
    }

    public void addPoints(int points) {
        score += points;
    }

}
