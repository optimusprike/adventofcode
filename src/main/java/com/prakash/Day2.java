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

        // list.forEach(this::part1);
        list.forEach(this::p1);
        System.out.println("part1: " + result);
        result = 0;
        list.forEach(this::p1v2);
        System.out.println("p1v2: " + result);

        result = 0;
        list.forEach(this::p2);
        System.out.println("part2: " + result);
    }

    public void p1v2(String range) {
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
            if(validTwo(String.valueOf(left))) {
                this.result += left;
            }
        }
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
        var len = leftStr.length();
        if (len % 2 == 1 && rightStr.length() == len) {
            return;
        }

        var left = Long.valueOf(leftStr);
        var right = Long.valueOf(rightStr);

        for (; left <= right; left++) {
            var str = String.valueOf(left);
            if(validTwo(str) || validThree(str) || validFive(str) || validSeven(str) || validEleven(str) || validThirteen(str)) {
                this.result += left;
            }

            System.out.println("result: " + result);
        }

    }

    public boolean validTwo(String str) {
        if (str.length() % 2 != 0) return false;

        var len = str.length();
        var divisor = len / 2;

        return str.substring(0, divisor).equals(str.substring(divisor));
    }

    public boolean validThree(String str) {
        if (str.length() % 3 != 0) return false;

        var len = str.length();
        var divisor = len / 3;
        var num = Long.parseLong(str);

        var lastThird = (long) (num % Math.pow(10, divisor));
        var remain = (long)Math.floor(num / Math.pow(10, divisor));
        var middleThird = (long) (remain % Math.pow(10, divisor));
        var frontThird = (long) Math.floor(remain / Math.pow(10, divisor));

        return frontThird == middleThird && middleThird == lastThird;
    }

    public boolean validFive(String str) {
        if (str.length() % 5 != 0) return false;

        var len = str.length();
        var divisor = len / 5;
        var num = Long.parseLong(str);

        var lastFifth = (long) (num % Math.pow(10, divisor));
        var firstFifth = (long) Math.floor(num / Math.pow(10, len - divisor));

        var middle = (long) Math.floor((num - (firstFifth * (Math.pow(10, len - divisor))) - lastFifth) / Math.pow(10, divisor));
        var middleBack = (long) (middle % Math.pow(10, divisor));

        return firstFifth == lastFifth && firstFifth == middleBack && validThree(String.valueOf(middle));
    }

    public boolean validSeven(String str) {
        if (str.length() % 7 != 0) return false;

        var len = str.length();
        var divisor = len / 7;
        var num = Long.parseLong(str);

        var lastSeventh = (long) (num % Math.pow(10, divisor));
        var firstSeventh = (long) Math.floor(num / Math.pow(10, len - divisor));

        var middle = (long) Math.floor((num - (firstSeventh * (Math.pow(10, len - divisor))) - lastSeventh) / Math.pow(10, divisor));
        var middleBackSeventh = (long) (middle % Math.pow(10, divisor));

        return firstSeventh == lastSeventh && firstSeventh == middleBackSeventh && validFive(String.valueOf(middle));
    }

    public boolean validEleven(String str) {
        if (str.length() % 11 != 0) return false;

        var len = str.length();
        var divisor = len / 11;
        var num = Long.parseLong(str);

        var lastEleventh = (long) (num % Math.pow(10, divisor));
        var firstEleventh = (long) Math.floor(num / Math.pow(10, len - divisor));

        var middle = (long) Math.floor((num - (firstEleventh * (Math.pow(10, len - divisor))) - lastEleventh) / Math.pow(10, divisor));
        var middleBackEleventh = (long) (middle % Math.pow(10, divisor));

        return firstEleventh == lastEleventh && firstEleventh == middleBackEleventh && validThree(String.valueOf(middle));
    }

    public boolean validThirteen(String str) {
        if (str.length() % 13 != 0) return false;

        var len = str.length();
        var divisor = len / 13;
        var num = Long.parseLong(str);

        var lastThirteenth = (long) (num % Math.pow(10, divisor));
        var firstThirteenth = (long) Math.floor(num / Math.pow(10, len - divisor));

        var middle = (long) Math.floor((num - (firstThirteenth * (Math.pow(10, len - divisor))) - lastThirteenth) / Math.pow(10, divisor));
        var middleBackThirteenth = (long) (middle % Math.pow(10, divisor));

        return firstThirteenth == lastThirteenth && firstThirteenth == middleBackThirteenth && validEleven(String.valueOf(middle));
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
    }
}
