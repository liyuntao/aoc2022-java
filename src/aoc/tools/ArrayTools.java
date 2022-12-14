package aoc.tools;

public class ArrayTools {
    public static long[][] initEmptyMatrix(int m, int n) {
        long[][] res = new long[m][n];
        for (int i = 0; i < n; i++) {
            res[i] = new long[m];
        }
        return res;
    }

    public static void debugMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void debugMatrix(IntMatrix matrix) {
        for (int[] line : matrix.inner) {
            for (int i : line) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
