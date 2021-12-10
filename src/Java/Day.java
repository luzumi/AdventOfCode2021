package Java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public abstract class Day {
    protected String[] txtInput;


    public Day(String fileName) {

        try {
            txtInput = Files.readString(Path.of("src/Ressources/" + fileName)).split("\n");
            System.out.println(txtInput.length);
        } catch (IOException e) {
            e.printStackTrace();
            e.getLocalizedMessage();
        }
    }

    public abstract String solveDayPartOne();

    public abstract String solveDayPartTwo();
}
