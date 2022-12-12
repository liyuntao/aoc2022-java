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

}
