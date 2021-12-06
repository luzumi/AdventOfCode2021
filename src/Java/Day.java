package Java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day {
    protected String[] txtInput;


    public Day(String fileName) {

        try {
            txtInput = Files.readString(Path.of("src/Ressources/" + fileName)).split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
