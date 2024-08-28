import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTest {
    private Statistics statistics;
    List<MatchResult> testMR = List.of(new MatchResult("Danmark-Kroation", List.of("Delaney", "Højlund", "Poulsen")));

    @Test
    public void getGoalScorers() throws FileNotFoundException {
        //Arrange
        Set<String> goalScorerNamesTest = new HashSet<>();

        for (MatchResult mr : testMR) {
            var goalScorerNamesToGet = mr.getGoalscorers();
            goalScorerNamesTest.addAll(goalScorerNamesToGet);
        }

        //Act
        Set<String> expectedNames = Set.of("Delaney", "Højlund", "Poulsen");
        Set<String> actualNames = goalScorerNamesTest;

        //Assert
        Assertions.assertEquals(expectedNames,actualNames);
    }

    @Test
    public void getGoalScorersWithTotal() {
        //Arrange
        HashMap<String, Integer> goalScorersTotalTestActual = new HashMap<>();
        HashMap<String, Integer> goalScorersTotalTestExpected = new HashMap<>();


        //Act
        for (MatchResult mr : testMR) {
            List<String> goalScorersTest = mr.getGoalscorers();

            for (String scorer : goalScorersTest) {
                goalScorersTotalTestActual.merge(scorer, 1, Integer::sum);
            }
        }

        goalScorersTotalTestExpected.put("Delaney", 1);
        goalScorersTotalTestExpected.put("Højlund", 1);
        goalScorersTotalTestExpected.put("Poulsen", 1);

        //Assert
        Assertions.assertEquals(goalScorersTotalTestActual,goalScorersTotalTestExpected);
    }

    @Test
    public void getNumberOfGoals() throws FileNotFoundException {
        //Arrange
        Statistics statistics = new Statistics("src/Euro2024QualifyingRound.csv");
        String goalScorer = "Højlund";

        //Act
        int expectedValue = 7;
        int actualValue = statistics.getNumbersOfGoals(goalScorer);

        //Assert
        Assertions.assertEquals(expectedValue, actualValue);
    }

}