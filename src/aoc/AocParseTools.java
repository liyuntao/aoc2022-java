package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
}
