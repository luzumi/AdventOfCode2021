package Java;

import java.awt.*;
import java.util.Arrays;


public class Day5 extends Day {
    public Day5(String fileName) {
        super(fileName);
    }

    int size = 1000;
    int[][] diagramm = new int[size][size];
    Point coordinate1;
    Point coordinate2;
    int counter = 0;

    //--- Day 5: Hydrothermal Venture ---
//
//You come across a field of hydrothermal vents on the ocean floor! These vents constantly produce large, opaque clouds, so it would be best to avoid them if possible.
//
//They tend to form in lines; the submarine helpfully produces a list of nearby lines of vents (your puzzle input) for you to review. For example:
//
//0,9 -> 5,9
//8,0 -> 0,8
//9,4 -> 3,4
//2,2 -> 2,1
//7,0 -> 7,4
//6,4 -> 2,0
//0,9 -> 2,9
//3,4 -> 1,4
//0,0 -> 8,8
//5,5 -> 8,2
//
//Each line of vents is given as a line segment in the format x1,y1 -> x2,y2 where x1,y1 are the coordinates of one end the line segment and x2,y2 are the coordinates of the other end. These line segments include the points at both ends. In other words:
//
//    An entry like 1,1 -> 1,3 covers points 1,1, 1,2, and 1,3.
//    An entry like 9,7 -> 7,7 covers points 9,7, 8,7, and 7,7.
//
//For now, only consider horizontal and vertical lines: lines where either x1 = x2 or y1 = y2.
//
//So, the horizontal and vertical lines from the above list would produce the following diagram:
//
//.......1..
//..1....1..
//..1....1..
//.......1..
//.112111211
//..........
//..........
//..........
//..........
//222111....
//
//In this diagram, the top left corner is 0,0 and the bottom right corner is 9,9. Each position is shown as the number of lines which cover that point or . if no line covers that point. The top-left pair of 1s, for example, comes from 2,2 -> 2,1; the very bottom row is formed by the overlapping lines 0,9 -> 5,9 and 0,9 -> 2,9.
//
//To avoid the most dangerous areas, you need to determine the number of points where at least two lines overlap. In the above example, this is anywhere in the diagram with a 2 or larger - a total of 5 points.
//
//Consider only horizontal and vertical lines. At how many points do at least two lines overlap?
    @Override
    public String solveDayPartOne() {
        createDiagram(true);


        return calculateCrossingPoints() + "";
    }


    //--- Part Two ---
    //
    //Unfortunately, considering only horizontal and vertical lines doesn't give you the full picture; you need to also consider diagonal lines.
    //
    //Because of the limits of the hydrothermal vent mapping system, the lines in your list will only ever be horizontal, vertical, or a diagonal line at exactly 45 degrees. In other words:
    //
    //    An entry like 1,1 -> 3,3 covers points 1,1, 2,2, and 3,3.
    //    An entry like 9,7 -> 7,9 covers points 9,7, 8,8, and 7,9.
    //
    //Considering all lines from the above example would now produce the following diagram:
    //
    //1.1....11.
    //.111...2..
    //..2.1.111.
    //...1.2.2..
    //.112313211
    //...1.2....
    //..1...1...
    //.1.....1..
    //1.......1.
    //222111....
    //
    //You still need to determine the number of points where at least two lines overlap. In the above example, this is still anywhere in the diagram with a 2 or larger - now a total of 12 points.
    //
    //Consider all of the lines. At how many points do at least two lines overlap?
    @Override
    public String solveDayPartTwo() {
        diagramm = new int[size][size];

        counter = 0;
        createDiagram(false);

        //printDiagram();
        return calculateCrossingPoints() + "";
    }


    private void createDiagram(boolean isNotDiagonal) {
        for (int[] ints : diagramm) {
            Arrays.fill(ints, 0);
        }
        int count = 0;
        for (String s : txtInput) {
            String[] line = s.trim().split("->");
            coordinate1 = new Point(Integer.parseInt(line[0].split(",")[0].trim()), Integer.parseInt(line[0].split(",")[1].trim()));
            coordinate2 = new Point(Integer.parseInt(line[1].split(",")[0].trim()), Integer.parseInt(line[1].split(",")[1].trim()));
            setPoint(coordinate1, coordinate2, isNotDiagonal);
            count++;
        }
        System.out.println("count: " + count);

    }

    private void printLine(String[] line) {
        System.out.println(line[0] + " -> " + line[1]);
    }


    private int calculateCrossingPoints() {
        Arrays.stream(diagramm).flatMapToInt(Arrays::stream).filter(anInt -> anInt >= 2).forEach(anInt -> counter++);
        return counter;
    }

    private void setPoint(Point coordinate1, Point coordinate2, boolean isNotDiagonal) {
        if (isNotDiagonal) {
            switchCoordinates(coordinate1, coordinate2);
            for (int row = coordinate1.x; row <= coordinate2.x; row++) {
                for (int column = coordinate1.y; column <= coordinate2.y; column++) {

                    if (coordinate1.x == coordinate2.x || coordinate1.y == coordinate2.y) {
                        diagramm[row][column]++;
                    }
                }
            }
        } else { //TODO : Diagonal lines
            int x1 = coordinate1.x;
            int y1 = coordinate1.y;
            int x2 = coordinate2.x;
            int y2 = coordinate2.y;
            if (coordinate1.x == coordinate2.x || coordinate1.y == coordinate2.y) {
                switchCoordinates(coordinate1, coordinate2);
                for (int row = coordinate1.x; row <= coordinate2.x; row++) {
                    for (int column = coordinate1.y; column <= coordinate2.y; column++) {
                        diagramm[row][column]++;
                    }
                }
            } else {
                //00 -> 99
                if (x1 < x2 && y1 < y2) {
                    for (int row = coordinate1.x; row <= coordinate2.x; row++) diagramm[y1++][x1++]++;
                } else if (x1 > x2 && y1 > y2) { //99 -> 00
                    for (int row = coordinate1.y; row <= coordinate1.y; row++) diagramm[x2++][y2++]++;
                } else if (x1 < x2) { //09 -> 90  5,5 -> 8,2
                    try {
                        for (int row = coordinate1.x; row <= coordinate2.x; row++) diagramm[x1++][y1--]++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage() + " " + coordinate1.x + " " + coordinate1.y + " " + coordinate2.x + " " + coordinate2.y);
                    }
                } else { //90 -> 09   8,0 -> 0,8
                    // TODO : Check if this works
                    try {
                        for (int row = coordinate2.x; row <= coordinate1.x; row++) diagramm[y1++][x2++]++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage() + " " + coordinate1.x + " " + coordinate1.y + " " + coordinate2.x + " " + coordinate2.y);

                    }
                }
            }
        }
    }


    private void switchCoordinates(Point coordinate1, Point coordinate2) {
        if (coordinate1.x > coordinate2.x) {
            int temp = coordinate1.x;
            this.coordinate1.x = coordinate2.x;
            this.coordinate2.x = temp;
            temp = coordinate1.y;
            this.coordinate1.y = coordinate2.y;
            this.coordinate2.y = temp;
        } else if (coordinate1.y > coordinate2.y) {
            int temp = coordinate1.y;
            this.coordinate1.y = coordinate2.y;
            this.coordinate2.y = temp;
            temp = coordinate1.x;
            this.coordinate1.x = coordinate2.x;
            this.coordinate2.x = temp;
        }
    }


    private void printDiagram() {
        System.out.println("Diagramm: Point: " + coordinate1.x + "," + coordinate1.y + " -> " + coordinate2.x + "," + coordinate2.y);
        System.out.println(" .0 1 2 3 4 5 6 7 8 9");
        for (int row = 0; row < diagramm.length; row++) {

            System.out.print(row + ".");
            for (int column = 0; column < diagramm[row].length; column++) {
                String sign = diagramm[column][row] == 0 ? ". " : diagramm[column][row] + " ";
                System.out.print(sign);
            }
            System.out.println();
        }
        System.out.println();
    }


}
