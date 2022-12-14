package aoc;

import aoc.tools.AocParseTools;
import aoc.tools.CharMatrix;
import aoc.tools.Point;

import java.util.*;

// timeout
public class Day12Backtracking implements DayBase {

    private List<List<Point>> backtrackingEntry() {
        var matrix = AocParseTools.parseToCharMatrix("day12.txt");
        List<List<Point>> allAvailablePaths = new ArrayList<>();
        backtracking(matrix, new LinkedHashSet<>(), allAvailablePaths, 0, 0);
        return allAvailablePaths;
    }

    private void backtracking(CharMatrix matrix, // immutable 逻辑判断
                              LinkedHashSet<Point> pathRecorder, // for state-recording
                              List<List<Point>> solutions, // for succeed finished path;
                              int x, int y) { // recursion will try at (x,y), on next step.
        pathRecorder.add(Point.of(x, y));

        char curSymbol = matrix.get(x, y);
        if (curSymbol == 'E') {
            // succeed path: record whole path, then quit recursion
            var oneSuccPath = pathRecorder.stream().map(Point::clone).toList();
//            System.out.println(oneSuccPath.size() + "  " + Arrays.toString(oneSuccPath.toArray()));
            solutions.add(oneSuccPath);
        } else {
            // get valid surrounding coordinates, and try on each recursive
            for (Point surroundPoint : matrix.getAllSurrounds(x, y)) {
                var nextSymbol = matrix.get(surroundPoint.x, surroundPoint.y);

                // the stone will move on if:
                // 1. satisfy height rules
                // 2. target is not ever passed by
                if (canReach(curSymbol, nextSymbol)
                        && !pathRecorder.contains(surroundPoint)) {
                    backtracking(matrix, pathRecorder, solutions, surroundPoint.x, surroundPoint.y);
                    pathRecorder.remove(surroundPoint);
                }
            }
        }
    }

    private boolean canReach(char cur, char nextUp) {
        if (cur == 'S') {
            return nextUp <= 'b';
        } else if (nextUp == 'E') {
            return cur == 'z';
        } else {
            return nextUp - cur <= 1;
        }
    }

    public Number q1() {
        return backtrackingEntry().stream()
                .mapToInt(List::size)
                .min().getAsInt() - 1;
    }

    public Number q2() {
        return 1;
    }

}
