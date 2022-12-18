package aoc;


import aoc.tools.AocParseTools;

import java.util.*;
import java.util.stream.IntStream;

public class Day13 implements DayBase {

    private List /*<Int union List<Int>>*/ smartParse(String line) {
        var lowButFast = line.replaceAll("\\[", "[,")
                .replaceAll("]", ",]").split(",+");
        Stack<List> stack = new Stack();
        List lastPop = null;
        for (int i = 0; i < lowButFast.length; i++) {
            String token = lowButFast[i];
            if (token.equals("[")) {
                stack.push(new ArrayList());
            } else if (token.equals("]")) {
                lastPop = stack.pop();
                if (!stack.isEmpty()) {
                    stack.peek().add(lastPop);
                }
            } else {
                stack.peek().add(Integer.parseInt(token));
            }
        }
        return lastPop;
    }

    private String debugList(List unionList) {
        if (unionList.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (Object ele : unionList) {
            if (ele instanceof Integer) {
                sb.append(ele).append(",");
            } else {
                sb.append(debugList((List)ele));
            }
        }
        return sb.append("]").toString();
    }

    // 1=right order, 0=equals, -1=not right order
    private int pk(List l1, List l2) {
        var iter1 = l1.iterator();
        var iter2 = l2.iterator();
        var a = safeNext(iter1);
        var b = safeNext(iter2);
        while (a != null) {
            if (b == null) return -1;
            if (a instanceof Integer && b instanceof Integer) {
                if ((Integer) a > (Integer) b) return -1;
                if ((Integer) a < (Integer) b) return 1;
            } else {
                int listCompRes;
                if (a instanceof Integer) {
                    listCompRes = pk(List.of(a), (List)b);
                } else if (b instanceof Integer) {
                    listCompRes = pk((List)a, List.of(b));
                } else {
                    listCompRes = pk((List)a, (List)b);
                }
                if (listCompRes != 0) return listCompRes;
            }
            a = safeNext(iter1);
            b = safeNext(iter2);
        }
        return b == null ? 0 : 1;
    }

    private Object safeNext(Iterator iter) {
        if (iter.hasNext()) {
            return iter.next();
        }
        return null;
    }

    public Number q1() {
        List<List<String>> lists = AocParseTools.readInputToGroupsEvery("day13.txt", 3);
        return IntStream.range(0, lists.size()).map(i -> {
            var pkRes = pk(smartParse(lists.get(i).get(0)), smartParse(lists.get(i).get(1)));
            return pkRes >= 0 ? i + 1 : 0;
        }).sum();
    }

    public Number q2() {
        var added2 = List.of(List.of(2));
        var added6 = List.of(List.of(6));
        List<List> lists = new ArrayList();
        lists.add(added2);
        lists.add(added6);
        for (String line : AocParseTools.readInputToLines("day13.txt")) {
            if(!line.isEmpty()) lists.add(smartParse(line));
        }
        lists.sort((l1, l2) -> pk(l2, l1));
//        lists.forEach(it -> System.out.println(debugList(it)));
        return (1 + lists.indexOf(added2)) * (1 + lists.indexOf(added6));
    }

}

