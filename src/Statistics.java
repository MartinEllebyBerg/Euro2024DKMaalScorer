import java.io.FileNotFoundException;
import java.util.*;

public class Statistics {
    private List<MatchResult> matchResults;
    private HashMap<String, Integer> goalScorersWithTotal;

    public Statistics(String filename) throws FileNotFoundException {
        MatchResultFileReader matchResultFileReader = new MatchResultFileReader(filename);
        matchResults = matchResultFileReader.readFile();
    }

    public Set<String> getGoalScorers() {
        Set<String> goalScorers = new HashSet<>();
        for (MatchResult m : matchResults) {
            //List<String> scores = m.getGoalscorers();
            goalScorers.addAll(m.getGoalscorers());
        }
        return goalScorers;
    }

    public Map<String, Integer> getGoalScorersWithTotals() {
        goalScorersWithTotal = new HashMap<>(); // Opretter et ny HaspMap

        // Iterer over en liste af MatchResult objekter gemt i variablen matchResults
        for (MatchResult mr : matchResults) {
            // For hvert MatchResult-objekt henter den en liste over målscorere ved hjælp af getGoalScorers-metoden
            List<String> goalScorers = mr.getGoalscorers();


            // Metoden gennemgår derefter denne liste af målscorere og bruger merge-metoden fra HashMap til at opdatere
            // det samlede antal mål, hver spiller har scoret. Hvis spilleren allerede findes i kortet, øges værdien med 1;
            // ellers tilføjes spilleren med en startværdi på 1.
            for (String scorer : goalScorers) {
                goalScorersWithTotal.merge(scorer, 1, Integer::sum);
            }
        }
        return goalScorersWithTotal;
    }

    public int getNumbersOfGoals(String goalscorer) {
        Map<String, Integer> scorerWithTotals = getGoalScorersWithTotals();
        return scorerWithTotals.get(goalscorer);
    }
}
