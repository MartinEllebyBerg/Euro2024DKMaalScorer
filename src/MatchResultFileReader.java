import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchResultFileReader {

    private String filename;
    private List<MatchResult> matches;

    public MatchResultFileReader(String filename) throws FileNotFoundException {
        this.filename = filename;
        matches = new ArrayList<>();
    }

    public List<MatchResult> readFile() {
        try (Scanner scanner = new Scanner(new File(filename))) {

            while (scanner.hasNext()) { //sålænge der er en næste linje er denne sand og kører
                String line = scanner.nextLine(); //læser linjen
                String[] arrayParts = line.split(";"); //deler linjen op i hold og målscorer
                String teams = arrayParts[0]; //Her er variable til holdene (den ene side af semikolon)
                List<String> goalScorers = new ArrayList<>();

                if (arrayParts.length >= 2) {
                    String lastPart = arrayParts[1]; // her skal tjekkes for om der findes en index 1 eller out of bounce!
                    String[] splitLastPart = lastPart.split(","); //deler målscore op

                    for (int i = 0; i < splitLastPart.length; i++) {
                        goalScorers.add(splitLastPart[i]);
                    }
                }
                matches.add(new MatchResult(teams, goalScorers));

            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }
        return matches;
    }

    public List<MatchResult> getMatches() {
        return matches;
    }
}
