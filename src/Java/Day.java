package Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Day {
    protected String[] txtInput;


    public Day(String fileName) {

        try {
            txtInput = new BufferedReader(new FileReader("src/Ressources/" + fileName)).lines().toArray(String[]::new);
            System.out.println(txtInput.length);
        } catch (IOException e) {
            e.printStackTrace();
            e.getLocalizedMessage();
        }
    }

    public abstract String solveDayPartOne();

    public abstract String solveDayPartTwo();

}
