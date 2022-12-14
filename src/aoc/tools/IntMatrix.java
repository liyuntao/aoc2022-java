package aoc.tools;

import java.util.Arrays;

public class IntMatrix {
    public final int M;
    public final int N;
    public final int[][] inner;

    public IntMatrix(int[][] matrix) {
        this.M = matrix.length;
        this.N = matrix[0].length;
        this.inner = matrix;
    }

    public static IntMatrix newMatrix(int m, int n, int fillVal) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[n];
            Arrays.fill(matrix[i], fillVal);
        }
        return new IntMatrix(matrix);
    }

    public int get(int x, int y) {
        return inner[x][y];
    }

    public int get(Point p) {
        return inner[p.x][p.y];
    }

    public void set(int x, int y, int value) {
        inner[x][y] = value;
    }

    public void set(Point p, int value) {
        inner[p.x][p.y] = value;
    }

}
