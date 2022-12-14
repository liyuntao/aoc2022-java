package aoc;

import aoc.tools.*;

import java.util.*;

public class Day12 implements DayBase {

    private void dijkstra() {
        var charMatrix = AocParseTools.parseToCharMatrix("day12.txt");
        var disMatrix = IntMatrix.newMatrix(charMatrix.M, charMatrix.N, Integer.MAX_VALUE);

        var startP = charMatrix.findFirst('E');
        disMatrix.set(startP, 0);

        Set<Point> settled = new LinkedHashSet<>();
        Set<Point> unsettled = new LinkedHashSet<>();
        unsettled.add(startP);

        while (!unsettled.isEmpty()) {
            Point curP = getLowest(unsettled, disMatrix);
            unsettled.remove(curP);

            if (curP == null) {
                // optimistic: this chunk of unsettled must be a group of un-reachable nodes.
                // Just clean it all and skip
                unsettled.clear();
                continue;
            }

            // get surroundings
            var pList = charMatrix.getAllSurrounds(curP.x, curP.y);
            for (Point nextP : pList) {
                if (!settled.contains(nextP)) {
                    calculateMinDistance(curP, nextP, charMatrix, disMatrix);
                    unsettled.add(nextP);
                }
            }
            settled.add(curP);
        }
        var endP = charMatrix.findFirst('S');
        System.out.println("q1 min dist is " + disMatrix.get(endP));

        var allCoordinateOfA = charMatrix.findAllByElement('a');
        allCoordinateOfA.add(endP);

        var minDistOfMin = allCoordinateOfA.stream().mapToInt(disMatrix::get).min().getAsInt();
        System.out.println("q2 min dist is " + minDistOfMin);
    }

    private Point getLowest(Set<Point> unsettled, IntMatrix distState) {
        Point lowestDistancePoint = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Point p : unsettled) {
            int distance = distState.get(p.x, p.y);
            if (distance < lowestDistance) {
                lowestDistance = distance;
                lowestDistancePoint = Point.of(p.x, p.y);
            }
        }
        return lowestDistancePoint;
    }

    private void calculateMinDistance(Point curP, Point tarP,
                                      CharMatrix charMatrix, IntMatrix distState) {
        var cur = charMatrix.get(curP);
        var nextUp = charMatrix.get(tarP);
        if (!canReach(cur, nextUp)) return;

        var curDist = distState.get(curP);
        if (curDist + 1 < distState.get(tarP)) {
            distState.set(tarP, curDist + 1);
        }
    }

    private boolean canReach(char cur, char nextDown) {
        if (cur == 'E') {
            return nextDown == 'z';
        } else if (nextDown == 'S') {
            return cur <= 'b';
        } else {
            return cur - nextDown <= 1;
        }
    }

    public Object q1() {
        dijkstra();
        return "--";
    }

    public Object q2() {
        return "--";
    }

}
