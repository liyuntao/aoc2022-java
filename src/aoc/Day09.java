package aoc;


import aoc.tools.AocParseTools;
import aoc.tools.Tuple2;

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
        var exec = new Day09Executor();
        parseInputs().forEach(exec::executeMotion);
        return exec.countVisited();
    }

    public Number q2() {
        return 1;
    }

}

record Motion(char direction, int steps){}

class Day09Executor {
    int headX = 0;
    int headY = 0;
    int tailX = 0;
    int tailY = 0;
    private final Set<Tuple2<Integer, Integer>> visitedPoints;

    public Day09Executor() {
        visitedPoints = new HashSet<>();
    }

    private boolean isHeadTailAdjacent() {
        return Math.abs(headX - tailX) <= 1 && Math.abs(headY - tailY) <= 1;
    }

    public void executeMotion(Motion m) {
        for (int i = 0; i < m.steps(); i++) {
            tickHeadMove(m.direction());
            if (!isHeadTailAdjacent()) {
                tickTailFollow(m.direction());
            }
        }
    }

    private void tickHeadMove(char direction) {
        switch (direction) {
            case 'L' -> this.headX--;
            case 'R' -> this.headX++;
            case 'D' -> this.headY--;
            case 'U' -> this.headY++;
            default -> throw new RuntimeException("not possible");
        }
    }

    private void tickTailFollow(char headDirection) {
        visitedPoints.add(new Tuple2<>(this.tailX, this.tailY));
        switch (headDirection) {
            case 'L' -> {
                this.tailX = this.headX + 1;
                this.tailY = this.headY;
            }
            case 'R' -> {
                this.tailX = this.headX - 1;
                this.tailY = this.headY;
            }
            case 'D' -> {
                this.tailX = this.headX;
                this.tailY = this.headY + 1;
            }
            case 'U' -> {
                this.tailX = this.headX;
                this.tailY = this.headY - 1;
            }
            default -> throw new RuntimeException("not possible");
        }

        visitedPoints.add(new Tuple2<>(this.tailX, this.tailY));
    }

    public int countVisited() {
        return visitedPoints.size();
    }
}