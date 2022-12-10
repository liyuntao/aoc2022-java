package aoc;


import java.util.Arrays;

public class Day01 {

    public void q1() {
        var maxSumOfGroup = Arrays.stream(AocTools.readInputToStr("day01.txt")
                .replaceAll("\n", ",")
                .split(",,"))
                .mapToInt(groupLine -> Arrays.stream(groupLine.split(",")).mapToInt(Integer::parseInt).sum())
                .max()
                .getAsInt();
        System.out.println("result of q1=" + maxSumOfGroup);
    }

    public void q2() {
        var arr = Arrays.stream(AocTools.readInputToStr("day01.txt")
                        .replaceAll("\n", ",")
                        .split(",,"))
                .mapToInt(groupLine -> Arrays.stream(groupLine.split(",")).mapToInt(Integer::parseInt).sum())
                .sorted()
                .toArray();
        var result = arr[arr.length - 1] + arr[arr.length - 2] + arr[arr.length - 3];
        System.out.println("result of q2=" + result);
    }

}

