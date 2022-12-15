package aoc;


import aoc.tools.AocParseTools;

public class Day02 implements DayBase {

    private int q1Rule(char left, char right) {
        if (left == 'A' && right == 'X') {
            return 1 + 3;
        } else if (left == 'A' && right == 'Y') {
            return 2 + 6;
        } else if (left == 'A' && right == 'Z') {
            return 3 + 0;
        } else if (left == 'B' && right == 'X') {
            return 1 + 0;
        } else if (left == 'B' && right == 'Y') {
            return 2 + 3;
        } else if (left == 'B' && right == 'Z') {
            return 3 + 6;
        } else if (left == 'C' && right == 'X') {
            return 1 + 6;
        } else if (left == 'C' && right == 'Y') {
            return 2 + 0;
        } else if (left == 'C' && right == 'Z') {
            return 3 + 3;
        }
        throw new RuntimeException("not possible");
    }

    private int q2Rule(char left, char right) {
        if (left == 'A' && right == 'X') {
            return 3 + 0;
        } else if (left == 'A' && right == 'Y') {
            return 1 + 3;
        } else if (left == 'A' && right == 'Z') {
            return 2 + 6;
        } else if (left == 'B' && right == 'X') {
            return 1 + 0;
        } else if (left == 'B' && right == 'Y') {
            return 2 + 3;
        } else if (left == 'B' && right == 'Z') {
            return 3 + 6;
        } else if (left == 'C' && right == 'X') {
            return 2 + 0;
        } else if (left == 'C' && right == 'Y') {
            return 3 + 3;
        } else if (left == 'C' && right == 'Z') {
            return 1 + 6;
        }
        throw new RuntimeException("not possible");
    }

    public Number q1() {
        return AocParseTools.readInputToLines("day02.txt")
                .stream()
                .mapToInt(s -> q1Rule(s.charAt(0), s.charAt(2)))
                .sum();
    }

    public Number q2() {
        return AocParseTools.readInputToLines("day02.txt")
                .stream()
                .mapToInt(s -> q2Rule(s.charAt(0), s.charAt(2)))
                .sum();
    }

}

