package aoc;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 implements DayBase {
    record SensorBeacon(int sx, int sy, int bx, int by, int mdis) {
    }

    private static final Pattern RE = Pattern.compile("Sensor at x=(\\d+), y=(\\d+): closest beacon is at x=(\\d+), y=(\\d+)");

    private SensorBeacon parseLine(String line) {
        Matcher m = RE.matcher(line);
        if (m.find()) {
            var sx = Integer.parseInt(m.group(1));
            var sy = Integer.parseInt(m.group(2));
            var bx = Integer.parseInt(m.group(3));
            var by = Integer.parseInt(m.group(4));
            return new SensorBeacon(sx, sy, bx, by,
                    Math.abs(by - sy) + Math.abs(bx - sx)
            );
        }
        throw new RuntimeException("not possible");
    }

    private List<SensorBeacon> getInputs() {
        return null;
    }

    public Number q1() {
        return 1;
    }

    public Number q2() {
        return 1;
    }

}

