package aoc;


import aoc.tools.AocParseTools;
import aoc.tools.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Day14 implements DayBase {

    private Set<Point> parseInputs() {
        Set<Point> chart = new HashSet<>();
        AocParseTools.readInputToLines("day14.txt")
                .forEach(line -> {
                    var pointStrArr = line.split(" -> ");
                    var linePointList = Arrays.stream(pointStrArr).map(this::rawToPoint).toList();
                    for (int i = 0; i < linePointList.size() - 1; i++) {
                        drawRockLine(chart, linePointList.get(i), linePointList.get(i + 1));
                    }
                });
        return chart;
    }

    private Point rawToPoint(String raw) {
        var split = raw.split(",");
        return Point.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    private void drawRockLine(Set<Point> chart, Point from, Point to) {
        if (from.x == to.x) {
            var min = Math.min(from.y, to.y);
            var max = Math.max(from.y, to.y);
            IntStream.rangeClosed(min, max).forEach(i -> chart.add(Point.of(from.x, i)));
        } else {
            var min = Math.min(from.x, to.x);
            var max = Math.max(from.x, to.x);
            IntStream.rangeClosed(min, max).forEach(i -> chart.add(Point.of(i, from.y)));
        }
    }

    private int getBottom(Set<Point> chart) {
        return chart.stream().mapToInt(point -> point.y).max().getAsInt();
    }

    // return point of fall， or null=falling into the endless
    private Point tryDropQ1(Set<Point> chart, Point p, int bottomLine) {
        if (p.y > bottomLine) {
            return null;
        }

        if (!chart.contains(Point.of(p.x, p.y + 1))) {
            return tryDropQ1(chart, Point.of(p.x, p.y + 1), bottomLine);
        } else if (!chart.contains(Point.of(p.x - 1, p.y + 1))) {
            return tryDropQ1(chart, Point.of(p.x - 1, p.y + 1), bottomLine);
        } else if (!chart.contains(Point.of(p.x + 1, p.y + 1))) {
            return tryDropQ1(chart, Point.of(p.x + 1, p.y + 1), bottomLine);
        } else { // stop here
            return p;
        }
    }

    private Point tryDropQ2(Set<Point> chart, Point p, int infiniteBottom) {
        if (chart.contains(p)) {
            return null; // full
        }

        if (p.y + 1 == infiniteBottom) {
            return p;
        }

        if (!chart.contains(Point.of(p.x, p.y + 1))) {
            return tryDropQ2(chart, Point.of(p.x, p.y + 1), infiniteBottom);
        } else if (!chart.contains(Point.of(p.x - 1, p.y + 1))) {
            return tryDropQ2(chart, Point.of(p.x - 1, p.y + 1), infiniteBottom);
        } else if (!chart.contains(Point.of(p.x + 1, p.y + 1))) {
            return tryDropQ2(chart, Point.of(p.x + 1, p.y + 1), infiniteBottom);
        } else { // stop here
            return p;
        }
    }

    public Number q1() {
        var rockChart = parseInputs();
        var bottom = getBottom(rockChart);
        int cnt = 0;
        while (true) {
            Point fallP = tryDropQ1(rockChart, Point.of(500, 0), bottom);
            if (fallP == null) {
                return cnt;
            }
            rockChart.add(fallP);
            cnt++;
        }
    }

    public Number q2() {
        var rockChart = parseInputs();
        var infiniteBottom = getBottom(rockChart) + 2;
        int cnt = 0;
        while (true) {
            Point fallP = tryDropQ2(rockChart, Point.of(500, 0), infiniteBottom);
            if (fallP == null) {
                return cnt;
            }
            rockChart.add(fallP);
            cnt++;
        }
    }

}

