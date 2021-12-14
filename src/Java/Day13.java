package Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day13 extends Day {
    public Day13(String s) {
        super(s);
        readInput();
        size = getSize(input);
        grid = new String[size[1] + 1][size[0] + 1];
        for (int i = 0; i < size[1] + 1; i++) {
            for (int j = 0; j < size[0] + 1; j++) {
                grid[i][j] = ".";
            }
        }
    }

    String[] input;
    int[] size;
    String[][] grid;
    ArrayList<String> horizontalFold = new ArrayList<>();
    ArrayList<String> verticalFold = new ArrayList<>();
    int sum = 0;

    //--- Day 13: Transparent Origami ---
//
//You reach another volcanically active part of the cave. It would be nice if you could do some kind of thermal imaging, so you could tell ahead of time which caves are too hot to safely enter.
//
//Fortunately, the submarine seems to be equipped with a thermal camera! When you activate it, you are greeted with:
//
//Congratulations on your purchase! To activate this infrared thermal imaging
//camera system, please enter the code found on page 1 of the manual.
//
//Apparently, the Elves have never used this feature. To your surprise, you manage to find the manual; as you go to open it, page 1 falls out. It's a large sheet of transparent paper! The transparent paper is marked with random dots and includes instructions on how to fold it up (your puzzle input). For example:
//
//6,10
//0,14
//9,10
//0,3
//10,4
//4,11
//6,0
//6,12
//4,1
//0,13
//10,12
//3,4
//3,0
//8,4
//1,10
//2,14
//8,10
//9,0
//
//fold along y=7
//fold along x=5
//
//The first section is a list of dots on the transparent paper. 0,0 represents the top-left coordinate. The first value, x, increases to the right. The second value, y, increases downward. So, the coordinate 3,0 is to the right of 0,0, and the coordinate 0,7 is below 0,0. The coordinates in this example form the following pattern, where # is a dot on the paper and . is an empty, unmarked position:
//
//...#..#..#.
//....#......
//...........
//#..........
//...#....#.#
//...........
//...........
//...........
//...........
//...........
//.#....#.##.
//....#......
//......#...#
//#..........
//#.#........
//
//Then, there is a list of fold instructions. Each instruction indicates a line on the transparent paper and wants you to fold the paper up (for horizontal y=... lines) or left (for vertical x=... lines). In this example, the first fold instruction is fold along y=7, which designates the line formed by all the positions where y is 7 (marked here with -):
//
//...#..#..#.
//....#......
//...........
//#..........
//...#....#.#
//...........
//...........
//-----------
//...........
//...........
//.#....#.##.
//....#......
//......#...#
//#..........
//#.#........
//
//Because this is a horizontal line, fold the bottom half up. Some the dots might end up overlapping after the fold is complete, but dots will never appear exactly on a fold line. The result of doing this fold looks like this:
//
//#.##..#..#.
//#...#......
//......#...#
//#...#......
//.#.#..#.###
//...........
//...........
//
//Now, only 17 dots are visible.
//
//Notice, for example, the two dots in the bottom left corner before the transparent paper is folded; after the fold is complete, those dots appear in the top left corner (at 0,0 and 0,1). Because the paper is transparent, the dot just below them in the result (at 0,3) remains visible, as it can be seen through the transparent paper.
//
//Also notice that some dots can end up overlapping; in this case, the dots merge together and become a single dot.
//
//The second fold instruction is fold along x=5, which indicates this line:
//
//#.##.|#..#.
//#...#|.....
//.....|#...#
//#...#|.....
//.#.#.|#.###
//.....|.....
//.....|.....
//
//Because this is a vertical line, fold left:
//
//#####
//#...#
//#...#
//#...#
//#####
//.....
//.....
//
//The instructions made a square!
//
//The transparent paper is pretty big, so for now, focus on just completing the first fold. After the first fold in the example above, 17 dots are visible - dots that end up overlapping after the fold is completed count as a single dot.
//
//How many dots are visible after completing just the first fold instruction on your transparent paper?
    @Override
    public String solveDayPartOne() {
        readInput();
        fillBlankGrid();
        fold();

        return String.valueOf(sum);
    }

    //The first half of this puzzle is complete! It provides one gold star: *
    //--- Part Two ---
    //
    //Finish folding the transparent paper according to the instructions. The manual says the code is always eight capital letters.
    //
    //What code do you use to activate the infrared thermal imaging camera system?
    @Override
    public String solveDayPartTwo() {
        return "manual read the Display:  L R G P R E C B";
    }

    private void fold() {
        horizontalFold = new ArrayList<>();
        verticalFold = new ArrayList<>();
        boolean first = true;
        for (String line : input) {
            if (line.contains("y")) {
                foldHorzizontal(line.substring(line.indexOf("=") + 1));

            } else if (line.contains("x")) {
                foldVertical(line.substring(line.indexOf("=") + 1));

                if (first) {
                    getSum();
                    first = false;
                }
            }
        }

        printThisArray();

    }

    private void getSum() {
        for (String[] strings : grid) {
            for (String string : strings) {
                if (string.equals("#")) {
                    sum++;
                }
            }
        }
    }

    private void foldVertical(String s) {
        String[][] newGrid = new String[grid.length][Integer.parseInt(s)];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < Integer.parseInt(s); j++) {
                if (grid[i][grid[0].length - 1 - j].equals("#") || grid[i][j].equals("#")) {
                    newGrid[i][j] = "#";
                } else {
                    newGrid[i][j] = ".";
                }
            }
        }
        grid = newGrid;
    }

    private void foldHorzizontal(String s) {
        String[][] newGrid = new String[Integer.parseInt(s)][grid[0].length];
        for (int i = 0; i < Integer.parseInt(s); i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[grid.length - 1 - i][j].equals("#") || grid[i][j].equals("#")) {
                    newGrid[i][j] = "#";
                } else {
                    newGrid[i][j] = ".";
                }
            }
        }
        grid = newGrid;
    }

    private void fillBlankGrid() {
        for (String line : input) {
            String[] split = line.split(",");
            if (line.length() == 0) {
                break;
            } else {
                grid[Integer.parseInt(split[1])][Integer.parseInt(split[0])] = "#";
            }
        }
    }

    private void printThisArray() {
        for (String[] strings : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
    }

    private void readInput() {
        try {
            input = new BufferedReader(new FileReader("src/Ressources/inputDay13.txt")).lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] getSize(String[] lines) {
        int rowSize = Integer.MIN_VALUE;
        int colSize = Integer.MIN_VALUE;

        for (String line : lines) {
            if (line.length() == 0) {
                break;
            }
            String[] splitter = line.split(",");
            if (Integer.parseInt(splitter[0]) > colSize) {
                colSize = Integer.parseInt(line.split(",")[0]);
            }
            if (Integer.parseInt(splitter[1]) > rowSize) {
                rowSize = Integer.parseInt(line.split(",")[1]);
            }

        }
        return new int[]{colSize, rowSize};
    }
}

