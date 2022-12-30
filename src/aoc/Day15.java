package aoc;


import aoc.tools.AocParseTools;
import aoc.tools.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day15 implements DayBase {
    record SensorBeacon(int sx, int sy, int bx, int by) {
    }

    private static final Pattern RE = Pattern.compile("Sensor at x=(\\d+), y=(\\d+): closest beacon is at x=(\\d+), y=(\\d+)");

    private SensorBeacon parseLine(String line) {
        Matcher m = RE.matcher(line);
        if (m.find()) {
            return new SensorBeacon(
                    Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)),
                    Integer.parseInt(m.group(3)),
                    Integer.parseInt(m.group(4))
            );
        }
        throw new RuntimeException("not possible");
    }

    public Number q1() {
        return 1;
    }

    public Number q2() {
        return 1;
    }

}

