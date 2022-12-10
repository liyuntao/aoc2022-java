package aoc;


import java.util.Arrays;

public class Day01 implements DayBase {

    public Number q1() {
        return Arrays.stream(AocTools.readInputToStr("day01.txt")
                .replaceAll("\n", ",")
                .split(",,"))
                .mapToInt(groupLine -> Arrays.stream(groupLine.split(",")).mapToInt(Integer::parseInt).sum())
                .max()
                .getAsInt();
    }

    public Number q2() {
        var arr = Arrays.stream(AocTools.readInputToStr("day01.txt")
                        .replaceAll("\n", ",")
                        .split(",,"))
                .mapToInt(groupLine -> Arrays.stream(groupLine.split(",")).mapToInt(Integer::parseInt).sum())
                .sorted()
                .toArray();
        return arr[arr.length - 1] + arr[arr.length - 2] + arr[arr.length - 3];
    }

}

