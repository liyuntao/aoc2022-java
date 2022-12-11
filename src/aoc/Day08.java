package aoc;


import aoc.tools.AocParseTools;
import aoc.tools.ArrayTools;
import aoc.tools.IntMatrix;

public class Day08 implements DayBase {

    public Number q1() {
        int[][] inputs = AocParseTools.parseToIntMatrix("day08.txt");
        IntMatrix matrix = new IntMatrix(inputs);
        int counter = 0;
        for (int i = 0; i < matrix.N; i++) {
            for (int j = 0; j < matrix.M; j++) {
                if (isTallest(matrix, i, j)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private boolean isTallest(IntMatrix matrix, int x, int y) {
        return isOnTheEdge(matrix, x, y)
                || isTallestToLeft(matrix, x, y)
                || isTallestToRight(matrix, x, y)
                || isTallestToUp(matrix, x, y)
                || isTallestToDown(matrix, x, y);
    }

    private boolean isTallestToLeft(IntMatrix matrix, int x, int y) {
        var cur = matrix.get(x, y);
        for (int i = 0; i < x; i++) {
            if (matrix.get(i, y) >= cur) return false;
        }
        return true;
    }

    private boolean isTallestToRight(IntMatrix matrix, int x, int y) {
        var cur = matrix.get(x, y);
        for (int i = x + 1; i < matrix.N; i++) {
            if (matrix.get(i, y) >= cur) return false;
        }
        return true;
    }

    private boolean isTallestToUp(IntMatrix matrix, int x, int y) {
        var cur = matrix.get(x, y);
        for (int i = 0; i < y; i++) {
            if (matrix.get(x, i) >= cur) return false;
        }
        return true;
    }

    private boolean isTallestToDown(IntMatrix matrix, int x, int y) {
        var cur = matrix.get(x, y);
        for (int i = y + 1; i < matrix.M; i++) {
            if (matrix.get(x, i) >= cur) return false;
        }
        return true;
    }

    private boolean isOnTheEdge(IntMatrix matrix, int x, int y) {
        return x == 0 || y == 0 || x == matrix.M - 1 || y == matrix.N - 1;
    }

    public Number q2() {
        int[][] inputs = AocParseTools.parseToIntMatrix("day08.txt");
        IntMatrix matrix = new IntMatrix(inputs);
        long[][] score = ArrayTools.initEmptyMatrix(matrix.M, matrix.N);

        for (int i = 0; i < matrix.N; i++) {
            for (int j = 0; j < matrix.M; j++) {
                score[i][j] = score(matrix, i, j);
            }
        }

        // find max score
        long max = 0;
        for (int i = 0; i < matrix.N; i++) {
            for (int j = 0; j < matrix.M; j++) {
                if (score[i][j] > max) {
                    max = score[i][j];
                }
            }
        }
        return max;
    }

    private long score(IntMatrix matrix, int x, int y) {
        if (isOnTheEdge(matrix, x, y)) return 0;
        return leftScore(matrix, x, y)
                * rightScore(matrix, x, y)
                * upScore(matrix, x, y)
                * downScore(matrix, x, y);
    }

    private long leftScore(IntMatrix matrix, int x, int y) {
        var cur = matrix.get(x, y);
        var counter = 0;
        for (int i = x - 1; i >= 0; i--) {
            if (cur > matrix.get(i, y)) {
                counter++;
            } else {
                return counter + 1;
            }
        }
        return counter;
    }

    private long rightScore(IntMatrix matrix, int x, int y) {
        var cur = matrix.get(x, y);
        var counter = 0;
        for (int i = x + 1; i < matrix.N; i++) {
            if (cur > matrix.get(i, y)) {
                counter++;
            } else {
                return counter + 1;
            }
        }
        return counter;
    }

    private long upScore(IntMatrix matrix, int x, int y) {
        var cur = matrix.get(x, y);
        var counter = 0;
        for (int i = y - 1; i >= 0; i--) {
            if (cur > matrix.get(x, i)) {
                counter++;
            } else {
                return counter + 1;
            }
        }
        return counter;
    }

    private long downScore(IntMatrix matrix, int x, int y) {
        var cur = matrix.get(x, y);
        var counter = 0;
        for (int i = y + 1; i < matrix.M; i++) {
            if (cur > matrix.get(x, i)) {
                counter++;
            } else {
                return counter + 1;
            }
        }
        return counter;
    }

}

