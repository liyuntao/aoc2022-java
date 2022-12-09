package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AocTools {

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
}
