package com.prakash;

import io.vavr.control.Try;

import java.util.Arrays;

public class Day2 {

    private long result;

    public Day2() {
        this.result = 0L;
    }

    public void solve(String fileName) {
        var list = Try.of(() ->
                getClass().getClassLoader().getResourceAsStream(fileName)
        ).recover(Exception.class,
                ex -> null
        ).mapTry(row ->
                new String(row.readAllBytes())
        ).toJavaOptional().map(input ->
                Arrays.asList(input.split(","))
        ).get();

        list.forEach(this::p1);
        System.out.println("part1: " + result);

        result = 0;
        list.forEach(this::p2);
        System.out.println("part2: " + result);
    }

    public void p1(String range) {
        String[] split = range.split("-");
        var leftStr = split[0];
        var rightStr = split[1].split("\n")[0];
        var len = leftStr.length();
        if (len % 2 == 1 && rightStr.length() == len) {
            return;
        }

        var left = Long.valueOf(leftStr);
        var right = Long.valueOf(rightStr);

        for (; left <= right; left++) {
            var str = Long.toString(left);
            if (str.length() % 2 == 1) continue;
            var mid = str.length() / 2;
            if (str.substring(0, mid).equals(str.substring(mid))) this.result += left;
        }
    }

    public void p2(String range) {
        String[] split = range.split("-");
        var leftStr = split[0];
        var rightStr = split[1].split("\n")[0];

        var left = Long.valueOf(leftStr);
        var right = Long.valueOf(rightStr);

        for (; left <= right; left++) {
            var str = String.valueOf(left);
            if (validSplit(str, 2)
                    || validSplit(str, 3)
                    || validSplit(str, 5)
                    || validSplit(str, 7)
                    || validSplit(str, 11)
                    || validSplit(str, 13)
            ) {
                this.result += left;
            }
        }

        // System.out.println("result: " + result);

    }

    public boolean validSplit(String str, int split) {
        if (str.length() % split != 0) return false;
        int partLen = str.length() / split;
        String[] parts = new String[split];
        for (int i = 0; i < split; i++) {
            parts[i] = str.substring(i * partLen, (i + 1) * partLen);
        }
        var one = parts[0];
        for (int i = 1; i < split; i++) {
            if (!parts[i].equals(one)) return false;
        }
        return true;
    }
}
