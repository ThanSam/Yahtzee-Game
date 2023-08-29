import java.util.List;

public class Player {

    private String name;
    private List<Combination> availableCombinations;
    private int totalScore = 0;
    private int bonusScore = 0;
    private boolean bonusAwarded = false;


    public Player(String name, List<Combination> availableCombinations) {
        this.name = name;
        this.availableCombinations = availableCombinations;
    }

    public void addPoints(int points) {
        totalScore += points;
    }

    public void checkForBonus(int points) {
        if (!bonusAwarded) {
            bonusScore += points;
            if (bonusScore >= 63) {
                totalScore += 35;
                bonusAwarded = true;
            }
        }
    }

    public List<Combination> getAvailableCombinations() {
        return availableCombinations;
    }

    public void removeAvailableCombination(Combination combination) {
        availableCombinations.remove(combination);
    }


}
