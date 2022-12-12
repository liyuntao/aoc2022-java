package aoc;


import aoc.tools.AocParseTools;
import aoc.tools.Point;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day09 implements DayBase {

    private List<Motion> parseInputs() {
        return AocParseTools.readInputToLines("day09.txt").stream()
                .map(line -> {
                    var arr = line.split(" ");
                    return new Motion(arr[0].charAt(0), Integer.parseInt(arr[1]));
                })
                .toList();
    }

    public Number q1() {
        var exec = new Day9Executor(2);
        parseInputs().forEach(exec::executeMotion);
        return exec.countVisited();
    }

    public Number q2() {
        var exec = new Day9Executor(10);
        parseInputs().forEach(exec::executeMotion);
        return exec.countVisited();
    }

}

record Motion(char direction, int steps) {
}

class Day9Executor {
    private final int ropeLen;
    private final Point[] appState;
    private final Set<Point> visitedPoints;

    public Day9Executor(int ropeLen) {
        visitedPoints = new HashSet<>();
        // 预先加入原点，后面实现会精简。只需记每次tail移动后坐标
        visitedPoints.add(Point.of(0, 0));

        this.ropeLen = ropeLen;
        appState = new Point[ropeLen];
        for (int i = 0; i < ropeLen; i++) {
            appState[i] = Point.of(0, 0);
        }
    }

    private void recordVisited(int x, int y) {
        visitedPoints.add(Point.of(x, y));
    }

    private boolean isNotAdjacent(int p1, int p2) {
        return Math.abs(appState[p1].x - appState[p2].x) > 1
                || Math.abs(appState[p1].y - appState[p2].y) > 1;
    }

    private boolean isSameRow(int p1, int p2) {
        return appState[p1].y == appState[p2].y;
    }

    private boolean isSameCol(int p1, int p2) {
        return appState[p1].x == appState[p2].x;
    }

    public void executeMotion(Motion m) {
        for (int i = 0; i < m.steps(); i++) {
            tickHeadMove(m.direction());
            tickTailsMoveRecursive(1);
        }
    }

    private void tickHeadMove(char direction) {
        int HEAD_IDX = 0;
        switch (direction) {
            case 'L' -> appState[HEAD_IDX].x--;
            case 'R' -> appState[HEAD_IDX].x++;
            case 'D' -> appState[HEAD_IDX].y--;
            case 'U' -> appState[HEAD_IDX].y++;
            default -> throw new RuntimeException("not possible");
        }
    }

    // check previous point, and then choose whether to move cur point=$pointId
    private void tickTailsMoveRecursive(int pointId) {
        if (pointId >= this.ropeLen) return;

        if (isNotAdjacent(pointId - 1, pointId)) {
            if (isSameRow(pointId - 1, pointId)) { // move horizontally
                appState[pointId].x += appState[pointId - 1].x > appState[pointId].x? 1 : -1;
            } else if (isSameCol(pointId - 1, pointId)) { // move vertically
                appState[pointId].y += appState[pointId - 1].y > appState[pointId].y? 1 : -1;
            } else { // move diagonally
                appState[pointId].x += appState[pointId - 1].x > appState[pointId].x? 1 : -1;
                appState[pointId].y += appState[pointId - 1].y > appState[pointId].y? 1 : -1;
            }

            if (pointId == ropeLen - 1) {
                this.recordVisited(appState[pointId].x, appState[pointId].y);
            }
            tickTailsMoveRecursive(pointId + 1);
        }
    }

    public int countVisited() {
        return visitedPoints.size();
    }
}