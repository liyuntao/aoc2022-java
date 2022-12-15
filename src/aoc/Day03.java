package aoc;


import aoc.tools.AocParseTools;

import java.util.*;
import java.util.stream.IntStream;

public class Day03 implements DayBase {

    private List<String> splitQ1(String line) {
        return List.of(line.substring(0, line.length() / 2),
                line.substring(line.length() / 2));
    }

    private int analysisGeneric(List<String> lines) {
        char common = lines.stream()
                .map(this::lineToCharSet)
                .reduce((s1, s2) -> {
                    s1.retainAll(s2);
                    return s1;
                })
                .map(set -> set.iterator().next())
                .get();
        if (common >= 'a') {
            return common - 'a' + 1;
        } else {
            return common - 'A' + 27;
        }
    }

    private Set<Character> lineToCharSet(String line) {
        Set<Character> set1 = new HashSet<>();
        for (char c : line.toCharArray()) {
            set1.add(c);
        }
        return set1;
    }

    public Number q1() {
        return AocParseTools.readInputToLines("day03.txt")
                .stream()
                .map(this::splitQ1)
                .mapToInt(this::analysisGeneric)
                .sum();
    }

    public Number q2() {
        int pageSize = 3;
        List<String> lines = AocParseTools.readInputToLines("day03.txt");

        return IntStream.range(0, (lines.size() + pageSize - 1) / pageSize)
                .mapToObj(i -> lines.subList(i * pageSize, Math.min(pageSize * (i + 1), lines.size())))
                .mapToInt(this::analysisGeneric)
                .sum();
    }

}

