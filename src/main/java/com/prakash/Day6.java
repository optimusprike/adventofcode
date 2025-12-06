package com.prakash;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day6 implements Problem {

    @Override
    public void p1() {
        var grid = lines()
                .stream()
                .map(line -> Arrays.stream(line.split("\\s+"))
                        .filter(str -> !str.isBlank())
                        .toArray(String[]::new))
                .toArray(String[][]::new);

        int row = grid.length;
        int col = grid[0].length;
        long sum = 0L;
        for (int i = 0; i < col; i++) {
            long total = 0L;
            var sign = grid[grid.length - 1][i];
            for (int j = 0; j < row - 1; j++) {
                var num = Long.parseLong(grid[j][i]);
                total = sign.equals("*") ? total == 0 ? num : total * num : total + num;
            }
            sum += total;
        }

        System.out.println("part 1: " + sum);
    }

    @Override
    public void p2() {
        var grid = wut().stream().map(line ->
                Arrays.stream(line.split("\\s+"))
                        .filter(str -> !str.isBlank())
                        .toArray(String[]::new)
        ).toArray(String[][]::new);

        var signs = lines().getLast()
                .chars()
                .boxed()
                .map(c -> (char) c.intValue())
                .filter(c -> c != ' ')
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString()
                .toCharArray();

        int col = grid[0].length;
        long sum = 0L;
        for (int i = 0; i < col; i++) {
            long total = 0L;
            var sign = signs[i];
            var len = grid[0][i].length();
            for (int k = len - 1; k > -1; k--) {
                var num = 0;
                int count = 0;
                for (String[] strings : grid) {
                    var x = strings[i].length() > k ? strings[i].charAt(k) - '0' : 0;
                    if (x == 0) {
                        count++;
                        continue;
                    }
                    num = (num * (int) Math.pow(10, count + 1)) + x;
                    count = 0;
                }
                total = sign == '*' ? total == 0 ? num : total * num : total + num;
            }

            sum += total;
        }

        System.out.println("part 2: " + sum);

    }

    private List<String> wut() {
        var lastLine = lines().getLast();
        int i = 1;
        var lines = lines().stream()
                .filter(line -> !(line.charAt(0) == '+' || line.charAt(0) == '*'))
                .toList();

        int maxStringLen = lines.stream().map(String::length).max(Integer::compare).get();

        var stringBuilders = IntStream.rangeClosed(1, lines.size()).mapToObj(_ -> new StringBuilder()).toList();
        while (i < maxStringLen) {
            int spaces = 0;
            int start = i - 1;
            while (i < lastLine.length() && lastLine.charAt(i) == ' ') {
                i++;
                spaces++;
            }
            if (i >= lastLine.length()) {
                spaces += (maxStringLen - lastLine.length() + 1);
                i = maxStringLen;
            }

            for (int j = 0; j < lines.size(); j++) {
                var sb = stringBuilders.get(j);
                var line = lines.get(j);
                var temp = spaces;
                for (int k = start; k < start + spaces && k < line.length(); k++) {
                    sb.append(line.charAt(k) == ' ' ? '0' : line.charAt(k));
                    temp--;
                }
                while (temp-- > 0) {
                    sb.append('0');
                }
                if (sb.length() < line.length()) sb.append(' ');
            }
            i++;
        }

        return stringBuilders.stream().map(StringBuilder::toString).toList();
    }
}
