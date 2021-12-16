package Java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14 extends Day {

    public Day14(String s) {
        super(s);
    }

    //--- Day 14: Extended Polymerization ---
    //
    //The incredible pressures at this depth are starting to put a strain on your submarine. The submarine has polymerization equipment that would produce suitable materials to reinforce the submarine, and the nearby volcanically-active caves should even have the necessary input elements in sufficient quantities.
    //
    //The submarine manual contains instructions for finding the optimal polymer formula; specifically, it offers a polymer template and a list of pair insertion rules (your puzzle input). You just need to work out what polymer would result after repeating the pair insertion process a few times.
    //
    //For example:
    //
    //NNCB
    //
    //CH -> B
    //HH -> N
    //CB -> H
    //NH -> C
    //HB -> C
    //HC -> B
    //HN -> C
    //NN -> C
    //BH -> H
    //NC -> B
    //NB -> B
    //BN -> B
    //BB -> N
    //BC -> B
    //CC -> N
    //CN -> C
    //
    //The first line is the polymer template - this is the starting point of the process.
    //
    //The following section defines the pair insertion rules. A rule like AB -> C means that when elements A and B are immediately adjacent, element C should be inserted between them. These insertions all happen simultaneously.
    //
    //So, starting with the polymer template NNCB, the first step simultaneously considers all three pairs:
    //
    //    The first pair (NN) matches the rule NN -> C, so element C is inserted between the first N and the second N.
    //    The second pair (NC) matches the rule NC -> B, so element B is inserted between the N and the C.
    //    The third pair (CB) matches the rule CB -> H, so element H is inserted between the C and the B.
    //
    //Note that these pairs overlap: the second element of one pair is the first element of the next pair. Also, because all pairs are considered simultaneously, inserted elements are not considered to be part of a pair until the next step.
    //
    //After the first step of this process, the polymer becomes NCNBCHB.
    //
    //Here are the results of a few steps using the above rules:
    //
    //Template:     NNCB
    //After step 1: NCNBCHB
    //After step 2: NBCCNBBBCBHCB
    //After step 3: NBBBCNCCNBBNBNBBCHBHHBCHB
    //After step 4: NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB
    //
    //This polymer grows quickly. After step 5, it has length 97; After step 10, it has length 3073. After step 10, B occurs 1749 times, C occurs 298 times, H occurs 161 times, and N occurs 865 times; taking the quantity of the most common element (B, 1749) and subtracting the quantity of the least common element (H, 161) produces 1749 - 161 = 1588.
    //
    //Apply 10 steps of pair insertion to the polymer template and find the most and least common elements in the result. What do you get if you take the quantity of the most common element and subtract the quantity of the least common element?
    @Override
    public String solveDayPartOne() {
        StringBuilder polymer = new StringBuilder(txtInput[0]);
        String[][] rules = new String[txtInput.length - 2][2];

        findRules(rules);
        String calculatedPolymer = calculatePolymer(polymer, rules, 10);

        return String.valueOf(findMinMax(calculatedPolymer));
    }

    //The first half of this puzzle is complete! It provides one gold star: *
    //--- Part Two ---
    //
    //The resulting polymer isn't nearly strong enough to reinforce the submarine. You'll need to run more steps of the pair insertion process; a total of 40 steps should do it.
    //
    //In the above example, the most common element is B (occurring 2192039569602 times) and the least common element is H (occurring 3849876073 times); subtracting these produces 2188189693529.
    //
    //Apply 40 steps of pair insertion to the polymer template and find the most and least common elements in the result. What do you get if you take the quantity of the most common element and subtract the quantity of the least common element?
    @Override
    public String solveDayPartTwo() {

        return String.valueOf(calculatePart2(40));
    }

    private long calculatePart2(int steps) {
        Map<String, Character> rules = new HashMap<>();
        Map<String, Long> pairs = new HashMap<>();
        Map<String, Long> temppairs = new HashMap<>();
        Map<Character, Long> counts = new HashMap<>();

        String code = "";

        try {
            File myObj = new File("src/Ressources/inputDay14.txt");
            Scanner myReader = new Scanner(myObj);
            code = myReader.nextLine();
            myReader.nextLine();

            while (myReader.hasNextLine()) {
                String rule = myReader.nextLine();
                String[] temp = rule.split(" -> ");
                rules.put(temp[0], temp[1].charAt(0));
                pairs.put(temp[0], 0L);
                temppairs.put(temp[0], 0L);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < code.length()-1; i++) {
            String polymer = code.substring(i,i+2);
            pairs.put(polymer, (pairs.get(polymer)+1));
        }

        for (int i = 0; i < steps; i++) {
            for (String s : pairs.keySet()) {

                String pairOne = "" + s.charAt(0) + rules.get(s);
                String pairTwo = "" + rules.get(s) + s.charAt(1);

                if (pairs.get(s) > 0) {
                    temppairs.put(pairOne, (temppairs.get(pairOne) + pairs.get(s)));
                    temppairs.put(pairTwo, (temppairs.get(pairTwo) + pairs.get(s)));
                }
            }
            for (String c : temppairs.keySet()) {
                pairs.put(c , temppairs.get(c));
                temppairs.put(c, 0L);
            }
        }

        counts.put(code.charAt(code.length()-1), 1L);

        for (String s : pairs.keySet()) {
            counts.putIfAbsent(s.charAt(0), 0L);
            counts.put(s.charAt(0), (counts.get(s.charAt(0)) + pairs.get(s)));
        }

        return Collections.max(counts.values()) - Collections.min(counts.values());

    }

    private void findRules(String[][] rules) {
        for (int i = 0; i < rules.length; i++) {
            rules[i][0] = txtInput[i + 2].substring(0, txtInput[i + 2].indexOf(" "));
            rules[i][1] = txtInput[i + 2].substring(txtInput[i + 2].indexOf("> ") + 2);
        }
    }

    private long findMinMax(String polymer) {

        return countDifference(getArrayList(polymer));
    }

    private ArrayList<Character> getArrayList(String polymer) {
        ArrayList<Character> list = new ArrayList<>();

        for (char c : polymer.toCharArray()) {
            list.add(c);
        }

        return list;
    }

    private int countDifference(ArrayList<Character> polymer) {
        Map<Character, Integer> occurrences = new HashMap<>();
        int max = 0;
        int min = 0;

        for (Character c : polymer) {
            if (occurrences.get(c) == null) {
                occurrences.put(c, 1);
            } else {
                occurrences.replace(c, occurrences.get(c) + 1);
            }
        }

        for (Integer val : occurrences.values()) {
            if ((max == 0) || (val > max)) { max = val; }
            if ((min == 0) || (val < min)) { min = val; }
        }

        return max - min;
    }

    private String calculatePolymer(StringBuilder polymer, String[][] rules, int rounds) {
        String nextPolymer;
        for (int k = 0; k < rounds; k++) {
            nextPolymer = "";
            int ploymerLenght = polymer.length() - 1;
            nextPolymer += (polymer.charAt(0));
            StringBuilder nextPolymerBuilder = new StringBuilder(nextPolymer);
            for (int i = 0; i < ploymerLenght; i++) {
                for (String[] rule : rules) {
                    if (polymer.substring(i, i + 2).equals(rule[0])) {
                        nextPolymerBuilder.append(rule[1]);
                        break;
                    }
                }
                nextPolymerBuilder.append(polymer.charAt(i + 1));
            }
            nextPolymer = nextPolymerBuilder.toString();
            polymer = new StringBuilder(nextPolymer);
        }

        return polymer.toString();
    }
}
