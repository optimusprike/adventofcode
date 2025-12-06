package com.prakash;

import io.vavr.control.Try;

import java.util.Arrays;

public class Day1 implements Problem {
    private int start;
    private int result;

    public Day1() {
        this.start = 50;
        this.result = 0;
    }

    public void solve(String fileName) {
        var list = Try.of(() ->
                getClass().getClassLoader().getResourceAsStream(fileName)
        ).recover(Exception.class,
                ex -> null
        ).mapTry(row ->
                new String(row.readAllBytes())
        ).toJavaOptional().map(input ->
                Arrays.asList(input.split("\n"))
        ).get();

        this.start = 50;
        this.result = 0;

        list.forEach(this::processPart1);
        System.out.println("part1: " + result);

        this.start = 50;
        this.result = 0;

        list.forEach(this::processPart2);
        System.out.println("part2 my way: " + result);
    }

    @Override
    public void p1() {
        lines().stream()
                .map(line -> new String[]{line.substring(0,1), line.substring(1)})

        var direction = move.substring(0, 1);
        var amount = Integer.parseInt(move.substring(1));
        amount %= 100;
        var newPos = direction.equals("L") ? start - amount : start + amount;
        if (newPos < 0 && start != 0) {
            newPos += 100;
        }
        if (newPos > 100 && start != 0) {
            newPos -= 100;
        }
        if (newPos == 0 || newPos == 100) {
            newPos = 0;
            this.result++;
        }
        start = newPos;
    }

    private void processPart2(String move) {
        var direction = move.substring(0, 1);
        var amount = Integer.parseInt(move.substring(1));
        this.result += (amount / 100);
        amount %= 100;
        var newPos = direction.equals("L") ? start - amount : start + amount;
        if (newPos < 0) {
            newPos += 100;
            if (start != 0) this.result++;
        }
        if (newPos > 100) {
            newPos -= 100;
            if (start != 0) this.result++;
        }
        if (newPos == 0 || newPos == 100) {
            newPos = 0;
            if (start != 0) this.result++;
        }
        start = newPos;
    }
}
