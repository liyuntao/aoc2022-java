package aoc;

import aoc.tools.AocParseTools;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day10 implements DayBase {

    private List<Integer> parseInputs() {
        return AocParseTools.readInputToLines("day10.txt")
                .stream().map(line -> {
                    if ("noop".equals(line)) {
                        return null; // null represents noop
                    } else {
                        return Integer.parseInt(line.split(" ")[1]);
                    }
                }).toList();
    }

    private int[] executionIns(int totalCycle) {
        var instructions = this.parseInputs();
        int[] mem = new int[totalCycle + 1];
        mem[0] = 1;
        int curInsIndex = 0;
        int curAddInsCycleLeft = 1;
        int willAddValue = 0;
        int willAddAtCycle = -1;
        for (int i = 1; i <= totalCycle; i++) {
            var curIns = instructions.get(curInsIndex);
            if (curIns == null) { // exec noop, cost 1 cycle;
                mem[i] = mem[i - 1];
                curInsIndex += 1;
            } else {
                if (curAddInsCycleLeft == 1) { // first add-inst cycle
                    mem[i] = mem[i - 1]; // do nothing
                    curAddInsCycleLeft = 0;
                } else if (curAddInsCycleLeft == 0) { // second add-inst cycle
                    mem[i] = mem[i - 1];
                    curInsIndex += 1;
                    curAddInsCycleLeft = 1;

                    willAddValue = curIns;
                    willAddAtCycle = i + 1;
                } else {
                    throw new RuntimeException("panic..");
                }
            }

            if (i == willAddAtCycle) {
                mem[i] += willAddValue;
            }
        }
        return mem;
    }

    private void draw(int[] mem) {
        for (int i = 0; i < 240; i++) {
            if (i % 40 == 0) {
                System.out.println();
            }
            var iInRow = i % 40;
            var spriteAt = mem[i];
            if (iInRow >= spriteAt && iInRow <= spriteAt + 2) {
                System.out.print('#');
            } else {
                System.out.print(' ');
            }
        }
        System.out.println();
    }

    public Number q1() {
        int[] mem = executionIns(220);
//        System.out.println(Arrays.toString(mem));
        return Stream.of(20, 60, 100, 140, 180, 220)
                .mapToInt(i -> i * mem[i])
                .sum();
    }

    public Object q2() {
        int[] mem = executionIns(240);
        draw(mem);
        return "⬆️ ⬆️";
    }

}
