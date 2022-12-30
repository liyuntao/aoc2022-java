package aoc;

import aoc.tools.AocParseTools;

import java.util.*;
import java.util.stream.IntStream;

public class Day06 implements DayBase {

    private int parseMarketIndex(String input, int markerLen) {
        var charArr = input.toCharArray();

        for (int i = markerLen - 1; i < charArr.length; i++) {
            var set = new HashSet<>();
            IntStream.range(i - markerLen + 1, i + 1)
                    .forEach(it -> set.add(charArr[it]));
            if (set.size() == markerLen) {
                return i + 1;
            }
        }
        return -1;
    }

    public Number q1() {
        String input = AocParseTools.readInputToStr("day06.txt");
        return parseMarketIndex(input, 4);
    }

    public Number q2() {
        String input = AocParseTools.readInputToStr("day06.txt");
        return parseMarketIndex(input, 14);
    }
}
