package Java;

public class AdventOfCode {
    public static void main(String[] args) {
        Day1 day1 = new Day1("inputDay1.txt");
        day1.solveDayPartOne();
        System.out.println(day1.getCounterPartOne());
        day1.solveDayPartTwo();
        System.out.println(day1.getCounterPartTwo());

        Day2 day2 = new Day2("inputDay2.txt");
        System.out.println("Day2/1 " + day2.solveDayPartOne());
        System.out.println("Day2/2 " + day2.solveDayPartTwo());

        Day3 day3 = new Day3("inputDay3.txt");
        System.out.println("Day3/1 " + day3.solveDayPartOne());
        System.out.println("Day3/2 " + day3.solveDayPartTwo());

        Day4 day4 = new Day4("inputDay4.txt");
        System.out.println("Day4/1 " + day4.solveDayPartOne());
        System.out.println("Day4/2 " + day4.solveDayPartTwo());

    }
}
