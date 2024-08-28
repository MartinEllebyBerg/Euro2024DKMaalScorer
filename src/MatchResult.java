import java.util.List;

public class MatchResult {

    private String teams;
    private List<String> goalscorers;

    public MatchResult(String teams, List<String> goalscorers) {
        this.teams = teams;
        this.goalscorers = goalscorers;
    }

    public List<String> getGoalscorers() {
        return goalscorers;
    }

    @Override
    public String toString() {
        return "Teams:" + teams + " | Goalscorers: " + goalscorers + "\n";
    }
}
