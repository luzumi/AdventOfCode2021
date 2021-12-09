package Java;

import java.util.Arrays;
//TODO Testscenario erfolgreich (inputDay4 - Kopie.txt), Aufgabe aber falsche lösung (inputDay4.txt)
public class Day4 extends Day {
    public Day4(String fileName) {
        super(fileName);
    }

    String originalString = "";
    String[] luckyNumbers;
    int[][][] bingoFieldNumbers;
    boolean[][][] isBingoField;
    String[] numbers;

    @Override
    public String solveDayPartOne() {
        originalString = Arrays.toString(txtInput);

        createFields();

        return checkIsBingo() + "";
    }

    private void createFields() {
        numbers = formatString();
        bingoFieldNumbers = new int[numbers.length][5][5];
        isBingoField = new boolean[numbers.length][5][5];

        int counter = 0;
        int fieldCounter = 0;
        while (counter < numbers.length * 5 - 1) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    isBingoField[fieldCounter][i][j] = false;
                    String[] toParse = new String[]{numbers[counter / 5]};
                    toParse = toParse[0].split(" ");
                    bingoFieldNumbers[fieldCounter][i][j] = Integer.parseInt(toParse[j]);
                    counter++;
                    if (counter % 25 == 0) {
                        fieldCounter++;
                        i = 0;
                        j = 0;
                    }
                }
            }
        }
    }

    private int checkIsBingo() {

        for (String luckyNumber : luckyNumbers) {
            for (int k = 0; k < bingoFieldNumbers.length; k++) {
                for (int j = 0; j < 5; j++) {
                    for (int l = 0; l < 5; l++) {
                        if (bingoFieldNumbers[k][j][l] == Integer.parseInt(luckyNumber)) {
                            isBingoField[k][j][l] = true;
                            int bingo = isBingo(luckyNumber, k, j, l);
                            if (bingo > 0) {
                                return bingo;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    private int isBingo(String luckyNumber, int k, int j, int l) {
        if (isBingoField[k][j][0] && isBingoField[k][j][1] && isBingoField[k][j][2] && isBingoField[k][j][3] && isBingoField[k][j][4]) {
            return calculateWinField(k, luckyNumber);
        }
        if (isBingoField[k][0][l] && isBingoField[k][1][l] && isBingoField[k][2][l] && isBingoField[k][3][l] && isBingoField[k][4][l]) {
            return calculateWinField(k, luckyNumber);
        }
        return 0;
    }

    private int calculateWinField(int i,  String luckyNumber) {
        int field = 0;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (!isBingoField[i][k][l]) {
                    field += bingoFieldNumbers[i][k][l];
                }
            }

        }
        return field * Integer.parseInt(luckyNumber);
    }

    private String[] formatString() {
        luckyNumbers = originalString.substring(1, originalString.indexOf(" ")).split(",");
        originalString = originalString.replaceAll("  ", " ");
        originalString = originalString.replaceAll(", ,", ",");
        originalString = originalString.substring(originalString.indexOf(", ") + 2, originalString.length() - 1);
        return originalString.split(", ");
    }

    @Override
    public String solveDayPartTwo() {
        return "";
    }

}
