package aoc.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AocParseTools {
    public static List<String> readInputToLines(String fileName) {
        try {
            return Files.readAllLines(Paths.get("./input/" + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readInputToStr(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get("./input/" + fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> firstNLines(List<String> input, int n) {
        List<String> lst = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lst.add(input.get(i));
        }
        return lst;
    }

    public static List<String> linesAfterN(List<String> input, int n) {
        List<String> lst = new ArrayList<>();
        for (int i = n; i < input.size(); i++) {
            lst.add(input.get(i));
        }
        return lst;
    }

    public static int[][] parseToIntMatrix(String fileName) {
        List<String> lines = readInputToLines(fileName);
        var M = lines.size(); // M rows and N cols
        var N = lines.get(0).length();
        var matrix = new int[M][N];
        for (int i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            var intArr = new int[N];
            for (int j = 0; j < line.length(); j++) {
                intArr[j] = line.charAt(j) - '0';
            }
            matrix[i] = intArr;
        }
        return matrix;
    }

    public static CharMatrix parseToCharMatrix(String fileName) {
        var lines = AocParseTools.readInputToLines(fileName);
        var M = lines.get(0).length();
        var N = lines.size();

        char[][] matrix = new char[N][M];
        for (int i = 0; i < N; i++) {
            matrix[i] = lines.get(i).toCharArray();
        }
        return CharMatrix.of(matrix);
    }
}
