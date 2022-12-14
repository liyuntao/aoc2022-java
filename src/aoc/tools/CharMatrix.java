package aoc.tools;

import java.util.List;
import java.util.stream.Stream;

public class CharMatrix {
    public final int M;
    public final int N;
    public final char[][] inner;

    protected CharMatrix(char[][] matrix) {
        this.M = matrix.length;
        this.N = matrix[0].length;
        this.inner = matrix;
    }

    public static CharMatrix of(char[][] matrix) {
        return new CharMatrix(matrix);
    }

    public char get(int x, int y) {
        return inner[x][y];
    }

    public List<Point> getAllSurrounds(int x, int y) {
        return Stream.of(Point.of(x + 1, y),
                Point.of(x - 1, y),
                Point.of(x, y - 1),
                Point.of(x, y + 1))
                .filter(p -> p.x >= 0 && p.x < M && p.y >= 0 && p.y < N)
                .toList();
    }

}
