package Java;

import java.util.ArrayList;
import java.util.Arrays;

public class Day6 extends Day {
    public Day6(String fileName) {
        super(fileName);
    }

    int days = 80;
    int i;
    ArrayList<Integer> fish;


    //--- Part Two ---
//
//Suppose the lanternfish live forever and have unlimited food and space. Would they take over the entire ocean?
//
//After 256 days in the example above, there would be a total of 26984457539 lanternfish!
//
//How many lanternfish would there be after 256 days?
    @Override
    public String solveDayPartOne() {
        String[] input = txtInput[0].split(",");
        fish = new ArrayList<>(input.length);

        for (String s : input) fish.add(Integer.valueOf(s));

        while (days > 0) {
            for (i = 0; i < fish.size(); i++) {
                if (fish.get(i) == 0) {
                    fish.set(i, 7);

                    fish.add(9);
                }
                fish.set(i, fish.get(i) - 1);
            }
            days--;
        }
        return fish.size() + " lanternfish would there be after 80 days";
    }


    @Override
    public String solveDayPartTwo() {
        days = 256;
        long[] fish = new long[10];
        for (String s : txtInput[0].split(",")) {
            fish[Integer.parseInt(s)]++;
        }

        for (int j = 0; j < days; j++) {
            fish[7] += fish[0];
            fish[9] = fish[0];
            System.arraycopy(fish, 1, fish, 0, 9);
            fish[9] = 0;
        }

        return Arrays.stream(fish).sum() + " lanternfish would there be after 256 days";
    }
}
