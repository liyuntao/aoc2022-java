package aoc.tools;

public class IntMatrix {
    public final int M;
    public final int N;
    public final int[][] inner;

    public IntMatrix(int[][] matrix) {
        this.M = matrix.length;
        this.N = matrix[0].length;
        this.inner = matrix;
    }

    public int get(int x, int y) {
        return inner[x][y];
    }

    public Integer safeUp(int a, int b) {
        int x = a;
        int y = b - 1;
        if (x < 0 || x >= M || y < 0 || y >= N) {
            return null;
        }
        return inner[x][y];
    }

    public Integer safeLeft(int a, int b) {
        int x = a - 1;
        int y = b;
        if (x < 0 || x >= M || y < 0 || y >= N) {
            return null;
        }
        return inner[x][y];
    }

}
