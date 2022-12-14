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
}
