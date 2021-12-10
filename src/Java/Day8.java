package Java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day8 extends Day {

    String[] input;

    public Day8(String fileName) {
        super(fileName);
    }

    @Override
    public String solveDayPartOne() {
        int count = 0;

        try {
            input = Files.newBufferedReader(Path.of("src/Ressources/inputDay8.txt")).lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : input) {
            String[] lines = s.substring(s.indexOf("|") + 1).split(" ");
            for (String value : lines) {
                if (value.length() == 2 || value.length() == 3 || value.length() == 4 || value.length() == 7) {
                    count++;
                }
            }
        }
        return String.valueOf(count);
    }

    @Override
    public String solveDayPartTwo() {
        return null;
    }
}
