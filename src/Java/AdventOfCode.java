package Java;

public class AdventOfCode {
    public static void main(String[] args) {
        Day day1 = new Day1("inputDay1.txt");
        day1.solveDayPartOne();
        System.out.println(day1.solveDayPartOne());
        day1.solveDayPartTwo();
        System.out.println(day1.solveDayPartTwo());

        Day day2 = new Day2("inputDay2.txt");
        System.out.println("Day2/1 " + day2.solveDayPartOne());
        System.out.println("Day2/2 " + day2.solveDayPartTwo());

        Day day3 = new Day3("inputDay3.txt");
        System.out.println("Day3/1 " + day3.solveDayPartOne());
        System.out.println("Day3/2 " + day3.solveDayPartTwo());

        Day day4 = new Day4("inputDay4.txt");
        System.out.println("Day4/1 " + day4.solveDayPartOne());
        System.out.println("Day4/2 " + day4.solveDayPartTwo());

        Day day5 = new Day5("inputDay5.txt");
        System.out.println("Day5/1 " + day5.solveDayPartOne());
        System.out.println("Day5/2 " + day5.solveDayPartTwo());

        Day day6 = new Day6("inputDay6.txt");
        System.out.println("Day6/1 " + day6.solveDayPartOne());
        System.out.println("Day6/2 " + day6.solveDayPartTwo());

    }
}
