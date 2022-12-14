package aoc.tools;

import java.util.ArrayList;
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

    public char get(Point p) {
        return inner[p.x][p.y];
    }

    public List<Point> getAllSurrounds(int x, int y) {
        return Stream.of(Point.of(x + 1, y),
                Point.of(x - 1, y),
                Point.of(x, y - 1),
                Point.of(x, y + 1))
                .filter(p -> p.x >= 0 && p.x < M && p.y >= 0 && p.y < N)
                .toList();
    }

    public Point findFirst(char element) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (get(j, i) == element) {
                    return Point.of(j, i);
                }
            }
        }
        return null;
    }

    public List<Point> findAllByElement(char element) {
        List<Point> res = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (get(j, i) == element) {
                    res.add(Point.of(j, i));
                }
            }
        }
        return res;
    }

}
