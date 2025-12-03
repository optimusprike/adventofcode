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

        list.forEach(this::part1);
        System.out.println("part1: " + result);
    }

    public void part1(String range) {
        String[] split = range.split("-");
        var leftStr = split[0];
        var rightStr = split[1].split("\n")[0];
        var len = leftStr.length();
        if (len % 2 == 1 && rightStr.length() == len) {
            return;
        }
        if (len % 2 == 1) {
            leftStr = String.valueOf((long) Math.pow(10, len));
            len = leftStr.length();
        }
        var mid = len / 2;

        var right = Long.parseLong(rightStr);
        var left = Long.parseLong(leftStr);

        var leftLeftStr = leftStr.substring(0, mid);
        var leftLeft = Long.parseLong(leftLeftStr);

        var combineStr = leftLeftStr + leftLeftStr;
        var combine = Long.parseLong(combineStr);

        while (combine >= left && combine <= right) {
            this.result += combine;
            leftLeft++;
            leftLeftStr = Long.toString(leftLeft);

            combineStr = leftLeftStr + leftLeftStr;
            combine = Long.parseLong(combineStr);
        }
//        var leftRightStr = leftStr.substring(mid);
//        var leftRight = Long.parseLong(leftRightStr);
//
//
//
//        var combine = leftLeftStr + leftLeftStr;
//
//
//        while(left <= right) {
//            var str = String.valueOf(left);
//            if(str.length() % 2 == 1) continue;
//            var mid = str.length() / 2;
//            if(str.substring(0, mid).equals(str.substring(mid))) this.result+= left;
//            left++;
//        }
    }
}
