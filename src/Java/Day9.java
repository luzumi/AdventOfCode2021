package Java;

import java.util.*;

public class Day9 extends Day {
    static ArrayList<Integer> numbers = new ArrayList<>();
    int[][] heightMap;

    public Day9(String fileName) {
        super(fileName);
    }

    //--- Day 9: Smoke Basin ---
//
//These caves seem to be lava tubes. Parts are even still volcanically active; small hydrothermal vents release smoke into the caves that slowly settles like rain.
//
//If you can model how the smoke flows through the caves, you might be able to avoid it and be that much safer. The submarine generates a heightmap of the floor of the nearby caves for you (your puzzle input).
//
//Smoke flows to the lowest point of the area it's in. For example, consider the following heightmap:
//
//2199943210
//3987894921
//9856789892
//8767896789
//9899965678
//
//Each number corresponds to the height of a particular location, where 9 is the highest and 0 is the lowest a location can be.
//
//Your first goal is to find the low points - the locations that are lower than any of its adjacent locations. Most locations have four adjacent locations (up, down, left, and right); locations on the edge or corner of the map have three or two adjacent locations, respectively. (Diagonal locations do not count as adjacent.)
//
//In the above example, there are four low points, all highlighted: two are in the first row (a 1 and a 0), one is in the third row (a 5), and one is in the bottom row (also a 5). All other locations on the heightmap have some lower adjacent location, and so are not low points.
//
//The risk level of a low point is 1 plus its height. In the above example, the risk levels of the low points are 2, 1, 6, and 6. The sum of the risk levels of all low points in the heightmap is therefore 15.
//
//Find all the low points on your heightmap. What is the sum of the risk levels of all low points on your heightmap?
    @Override
    public String solveDayPartOne() {
        heightMap = new int[txtInput.length][txtInput[0].length()];
        for (int i = 0; i < txtInput.length; i++) {
            for (int j = 0; j < txtInput[0].length(); j++) heightMap[i][j] = txtInput[i].charAt(j) - '0';
        }
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i == 0 && j == 0) {
                    if (heightMap[i][j + 1] > heightMap[i][j] && heightMap[i + 1][j] > heightMap[i][j]) {
                        sum += (1 + heightMap[i][j]);
                    }
                } else if (i == 0 && j == heightMap[i].length - 1) {
                    if (heightMap[i][j - 1] > heightMap[i][j] && heightMap[i + 1][j] > heightMap[i][j]) {
                        sum += (1 + heightMap[i][j]);
                    }
                } else if (i == heightMap.length - 1 && j == 0) {
                    if (heightMap[i][j + 1] > heightMap[i][j] && heightMap[i - 1][j] > heightMap[i][j]) {
                        sum += (1 + heightMap[i][j]);
                    }
                } else if (i == heightMap.length - 1 && j == heightMap[i].length - 1) {
                    if (heightMap[i][j - 1] > heightMap[i][j] && heightMap[i - 1][j] > heightMap[i][j]) {
                        sum += (1 + heightMap[i][j]);
                    }
                } else if (i == 0) {
                    if (heightMap[i][j - 1] > heightMap[i][j] && heightMap[i + 1][j] > heightMap[i][j]
                            && heightMap[i][j + 1] > heightMap[i][j]) {
                        sum += (1 + heightMap[i][j]);
                    }
                } else if (i == heightMap.length - 1) {
                    if (heightMap[i][j - 1] > heightMap[i][j] && heightMap[i - 1][j] > heightMap[i][j]
                            && heightMap[i][j + 1] > heightMap[i][j]) {
                        sum += (1 + heightMap[i][j]);
                    }
                } else if (j == 0) {
                    if (heightMap[i + 1][j] > heightMap[i][j] && heightMap[i - 1][j] > heightMap[i][j]
                            && heightMap[i][j + 1] > heightMap[i][j]) {
                        sum += (1 + heightMap[i][j]);
                    }
                } else if (j == heightMap.length - 1) {
                    if (heightMap[i + 1][j] > heightMap[i][j] && heightMap[i - 1][j] > heightMap[i][j]
                            && heightMap[i][j - 1] > heightMap[i][j]) {
                        sum += (1 + heightMap[i][j]);
                    }
                } else {
                    if (heightMap[i + 1][j] > heightMap[i][j] && heightMap[i - 1][j] > heightMap[i][j]
                            && heightMap[i][j - 1] > heightMap[i][j] && heightMap[i][j + 1] > heightMap[i][j]) {
                        sum += (1 + heightMap[i][j]);
                    }
                }
            }
        }
        return String.valueOf(sum);
    }

    //--- Part Two ---
    //
    //Next, you need to find the largest basins, so you know what areas are most important to avoid.
    //
    //A basin is all locations that eventually flow downward to a single low point. Therefore, every low point has a basin, although some basins are very small. Locations of height 9 do not count as being in any basin, and all other locations will always be part of exactly one basin.
    //
    //The size of a basin is the number of locations within the basin, including the low point. The example above has four basins.
    //
    //The top-left basin, size 3:
    //
    //2199943210
    //3987894921
    //9856789892
    //8767896789
    //9899965678
    //
    //The top-right basin, size 9:
    //
    //2199943210
    //3987894921
    //9856789892
    //8767896789
    //9899965678
    //
    //The middle basin, size 14:
    //
    //2199943210
    //3987894921
    //9856789892
    //8767896789
    //9899965678
    //
    //The bottom-right basin, size 9:
    //
    //2199943210
    //3987894921
    //9856789892
    //8767896789
    //9899965678
    //
    //Find the three largest basins and multiply their sizes together. In the above example, this is 9 * 14 * 9 = 1134.
    //
    //What do you get if you multiply together the sizes of the three largest basins?
    @Override
    public String solveDayPartTwo() {
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[i].length; j++) {
                if (i == 0 && j == 0) {
                    if (heightMap[i][j + 1] > heightMap[i][j] &&
                            heightMap[i + 1][j] > heightMap[i][j]) {
                        explore(i, j, heightMap);
                    }
                } else if (i == 0 && j == heightMap[i].length - 1) {
                    if (heightMap[i][j - 1] > heightMap[i][j] &&
                            heightMap[i + 1][j] > heightMap[i][j]) {
                        explore(i, j, heightMap);
                    }
                } else if (i == heightMap.length - 1 && j == 0) {
                    if (heightMap[i][j + 1] > heightMap[i][j] &&
                            heightMap[i - 1][j] > heightMap[i][j]) {
                        explore(i, j, heightMap);
                    }
                } else if (i == heightMap.length - 1 && j ==
                        heightMap[i].length - 1) {
                    if (heightMap[i][j - 1] > heightMap[i][j] &&
                            heightMap[i - 1][j] > heightMap[i][j]) {
                        explore(i, j, heightMap);
                    }
                } else if (i == 0) {
                    if (heightMap[i][j - 1] > heightMap[i][j] &&
                            heightMap[i + 1][j] > heightMap[i][j] &&
                            heightMap[i][j + 1] > heightMap[i][j]) {
                        explore(i, j, heightMap);
                    }
                } else if (i == heightMap.length - 1) {
                    if (heightMap[i][j - 1] > heightMap[i][j] &&
                            heightMap[i - 1][j] > heightMap[i][j] &&
                            heightMap[i][j + 1] > heightMap[i][j]) {
                        explore(i, j, heightMap);
                    }
                } else if (j == 0) {
                    if (heightMap[i + 1][j] > heightMap[i][j] &&
                            heightMap[i - 1][j] > heightMap[i][j] &&
                            heightMap[i][j + 1] > heightMap[i][j]) {
                        explore(i, j, heightMap);
                    }
                } else if (j == heightMap.length - 1) {
                    if (heightMap[i + 1][j] > heightMap[i][j] &&
                            heightMap[i - 1][j] > heightMap[i][j] &&
                            heightMap[i][j - 1] > heightMap[i][j]) {
                        explore(i, j, heightMap);
                    }
                } else {
                    if (heightMap[i + 1][j] > heightMap[i][j] &&
                            heightMap[i - 1][j] > heightMap[i][j] &&
                            heightMap[i][j - 1] > heightMap[i][j] &&
                            heightMap[i][j + 1] > heightMap[i][j]) {
                        explore(i, j, heightMap);
                    }
                }
            }
        }
        Collections.sort(numbers);
        Collections.reverse(numbers);
        return String.valueOf(numbers.get(0) * numbers.get(1) * numbers.get(2));
    }


    public static void explore(int i, int j, int[][] heightMap) {
        Stack<String> stack = new Stack<>();
        Set<String> set = new HashSet<>();
        int x, y, count;
        stack.push((i + "," + j));
        set.add((i + "," + j));
        count = 0;

        while (stack.size() > 0) {
            String pop = stack.pop();
            x = Integer.parseInt(pop.substring(0, pop.indexOf(",")));
            y = Integer.parseInt(pop.substring(pop.indexOf(",") + 1));

            if (y - 1 >= 0 && heightMap[x][y - 1] != 9) {
                int size = set.size();
                set.add((x) + "," + (y - 1));
                if (size != set.size()) {
                    stack.push((x) + "," + (y - 1));
                }
            }
            if (y + 1 < heightMap[x].length && heightMap[x][y + 1] != 9) {
                int size = set.size();
                set.add((x) + "," + (y + 1));
                if (size != set.size()) {
                    stack.push((x) + "," + (y + 1));
                }
            }
            if (x - 1 >= 0 && heightMap[x - 1][y] != 9) {
                int size = set.size();
                set.add((x - 1) + "," + (y));
                if (size != set.size()) {
                    stack.push((x - 1) + "," + (y));
                }
            }
            if (x + 1 < heightMap.length && heightMap[x + 1][y] != 9) {
                int size = set.size();
                set.add((x + 1) + "," + (y));
                if (size != set.size()) {
                    stack.push((x + 1) + "," + (y));
                }
            }
            if (heightMap[x][y] == 9) {
                count--;
            }
            count++;
        }
        numbers.add(count);
    }
}
