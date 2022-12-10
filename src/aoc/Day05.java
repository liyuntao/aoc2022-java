package aoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 implements DayBase {

    private static final Pattern ACTION_RE = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

    public String q1() {
        var appState = parseInitStacks();
        var actions = parseActions();

        actions.forEach(action -> executionQ1Action(action, appState));
        return peekTopOfEveryStack(appState);
    }

    public String q2() {
        var appState = parseInitStacks();
        var actions = parseActions();

        actions.forEach(action -> executionQ2Action(action, appState));
        return peekTopOfEveryStack(appState);
    }

    private String peekTopOfEveryStack(List<Stack<Character>> appState) {
        return appState.stream().skip(1)
                .map(stack -> stack.isEmpty() ? " " : stack.peek().toString())
                .reduce("", (a,b) -> a+b);
    }

    private void executionQ1Action(Action action, List<Stack<Character>> appState) {
        for (int i = 0; i < action.movTimes; i++) {
            char element = appState.get(action.from).pop();
            appState.get(action.to).push(element);
        }
    }

    private void executionQ2Action(Action action, List<Stack<Character>> appState) {
        List<Character> tmp = new ArrayList<>();
        for (int i = 0; i < action.movTimes; i++) {
            char element = appState.get(action.from).pop();
            tmp.add(element);
        }
        Collections.reverse(tmp);
        tmp.forEach(ele -> appState.get(action.to).push(ele));
    }

    private List<Stack<Character>> parseInitStacks() {
        var appState = List.of(
                new Stack<Character>(),
                new Stack<Character>(), // 1
                new Stack<Character>(),
                new Stack<Character>(),
                new Stack<Character>(),
                new Stack<Character>(),
                new Stack<Character>(),
                new Stack<Character>(),
                new Stack<Character>(),
                new Stack<Character>() // 9
        );
        var allInputs = AocParseTools.readInputToLines("day05.txt");
        var stackInputs = AocParseTools.firstNLines(allInputs, 8);
        Collections.reverse(stackInputs);
        stackInputs.forEach( line -> {
            Character[] parsed = parseStackLine(line);
            for (int i = 1; i <= 9; i++) {
                if (parsed[i] != null && parsed[i] != ' ') {
                    appState.get(i).push(parsed[i]);
                }
            }
        });
        return appState;
    }

    private List<Action> parseActions() {
        var allInputs = AocParseTools.readInputToLines("day05.txt");
        return AocParseTools.linesAfterN(allInputs, 10).stream()
                .map(this::parseActionLine)
                .toList();
    }

    private Character[] parseStackLine(String line) {
        return new Character[]{
                null, // 占用0列，方便后续
                safeCharAt(line, 1),
                safeCharAt(line, 5),
                safeCharAt(line, 9),
                safeCharAt(line, 13),
                safeCharAt(line, 17),
                safeCharAt(line, 21),
                safeCharAt(line, 25),
                safeCharAt(line, 29),
                safeCharAt(line, 33)
        };
    }

    private Character safeCharAt(String line, int index) {
        if (index < line.length()) {
            return line.charAt(index);
        }
        return null;
    }

    private Action parseActionLine(String line) {
        Matcher m = ACTION_RE.matcher(line);
        if (m.find()) {
            return new Action(Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)),
                    Integer.parseInt(m.group(3)));
        }
        throw new RuntimeException("not possible");
    }

    record Action(int movTimes, int from, int to){}
}
