package aoc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {

    private static final Pattern RE = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

    private int[] parseLine(String line) {
        Matcher m = RE.matcher(line);
        if (m.find()) {
            return new int[]{
                    Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)),
                    Integer.parseInt(m.group(3)),
                    Integer.parseInt(m.group(4))
            };
        }
        throw new RuntimeException("not possiable");
    }

    private boolean isContains(int a, int b, int c, int d) {
        if (a <= c && b >= d) {
            return true;
        } else if (a >= c && b <= d) {
            return true;
        }
        return false;
    }

    private boolean isOverlap(int a, int b, int c, int d) {
        if (c <= b && a <= d) {
            return true;
        }
        return false;
    }


    public void q1() {
        var q1 = AocTools.readInputToLines("day04.txt")
                .stream()
                .map(this::parseLine)
                .filter(arr -> isContains(arr[0], arr[1], arr[2], arr[3]))
                .count();
        System.out.println("result of q1=" + q1);
    }

    public void q2() {
        var q2 = AocTools.readInputToLines("day04.txt")
                .stream()
                .map(this::parseLine)
                .filter(arr -> isOverlap(arr[0], arr[1], arr[2], arr[3]))
                .count();
        System.out.println("result of q2=" + q2);
    }

}
