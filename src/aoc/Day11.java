package aoc;

import aoc.tools.AocParseTools;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day11 implements DayBase {

    record Behavior(Integer id, List<Long> items, boolean isPlus,
                    String optVal, Function<Long, Integer> testFn, int divisor) {
    }

    private static final Pattern RE = Pattern.compile("""
            Monkey (\\d+):
            \\s+Starting items: ([0-9, ]+)
            \\s+Operation: new = old ([+*]) (old|\\d+)
            \\s+Test: divisible by (\\d+)
            \\s+If true: throw to monkey (\\d+)
            \\s+If false: throw to monkey (\\d+)""");

    private List<Behavior> parseInputs() {
        var line = AocParseTools.readInputToStr("day11.txt");
        Matcher m = RE.matcher(line);
        List<Behavior> result = new ArrayList<>();
        while (m.find()) {
            var items = Arrays.stream(m.group(2).split(", ")).map(Long::parseLong).collect(Collectors.toList());
            var divisor = Integer.parseInt(m.group(5));
            var b = Integer.parseInt(m.group(6));
            var c = Integer.parseInt(m.group(7));
            Function<Long, Integer> testFn = i -> i % divisor == 0 ? b : c;
            var bh = new Behavior(Integer.parseInt(m.group(1)), items,
                    "+".equals(m.group(3)), m.group(4), testFn, divisor);
            result.add(bh);
        }
        return result;
    }

    private void exec(List<Behavior> inputs, int[] counterArr, int divBy, int magic) {
        inputs.forEach(bh -> {
            if (!bh.items().isEmpty()) {
                for (Long item : bh.items()) {
                    var target = "old".equals(bh.optVal) ? item : Integer.parseInt(bh.optVal);
                    var finalVal = bh.isPlus ? item + target : item * target;
                    finalVal = (finalVal / divBy) % magic;
                    var throwTo = bh.testFn.apply(finalVal);
                    inputs.get(throwTo).items().add(finalVal);
                    counterArr[bh.id] += 1;
                }
                bh.items().clear();
            }
        });
    }

    private long countScore(int[] values) {
        int largestA = Integer.MIN_VALUE, largestB = Integer.MIN_VALUE;

        for(int value : values) {
            if(value > largestA) {
                largestB = largestA;
                largestA = value;
            } else if (value > largestB) {
                largestB = value;
            }
        }
        return (long) largestA * largestB;
    }

    private long reduceEntry(int round, int divBy) {
        var inputs = parseInputs();
        var cntArr = new int[inputs.size()];
        int magicNo = inputs.stream().map(Behavior::divisor).reduce(1, (a, b) -> a * b);
        for (int i = 0; i < round; i++) {
            exec(inputs, cntArr, divBy, magicNo);
        }
        return countScore(cntArr);
    }

    public Number q1() {
        return reduceEntry(20, 3);
    }

    public Number q2() {
        return reduceEntry(10000, 1);
    }

}
