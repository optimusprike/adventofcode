package com.prakash;

import io.vavr.control.Try;

import java.util.Arrays;
import java.util.List;

public interface Problem {

    default int day() {
        String className = getClass().getSimpleName();
        String number = className.replaceAll("\\D+", "");
        return number.isEmpty() ? -1 : Integer.parseInt(number);
    }

    void p1(String fileName);

    void p2(String fileName);

    default String inputFile() {
        return "input_day" + day() + ".txt";
    }

    default String testFile() {
        return "test_day" + day() + ".txt";
    }

    default void solve() {
        System.out.println("solving real file");
        p1(inputFile());
        p2(inputFile());
    }

    default void solveTest() {
        System.out.println("solving test file");
        p1(testFile());
        p2(testFile());
    }

    default void solveBoth() {
        solveTest();
        solve();
    }

    default List<String> read(String fileName) {
        return Try.of(() ->
                getClass().getClassLoader().getResourceAsStream(fileName)
        ).recover(Exception.class,
                ex -> null
        ).mapTry(row ->
                new String(row.readAllBytes())
        ).toJavaOptional().map(input ->
                Arrays.asList(input.split("\n"))
        ).get();
    }
}
