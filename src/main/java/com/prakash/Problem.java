package com.prakash;

import io.vavr.control.Try;

import java.util.Arrays;
import java.util.List;

public interface Problem {

    ThreadLocal<Boolean> isTest = ThreadLocal.withInitial(() -> false);

    default String name() {
        return getClass().getSimpleName();
    }

    default List<String> lines() {
        String file = isTest.get() ? testFile() : inputFile();
        return read(file);
    }

    default int day() {
        String className = getClass().getSimpleName();
        String number = className.replaceAll("\\D+", "");
        return number.isEmpty() ? -1 : Integer.parseInt(number);
    }

    void p1();

    void p2();

    default String inputFile() {
        return "input_day" + day() + ".txt";
    }

    default String testFile() {
        return "test_day" + day() + ".txt";
    }

    default void solve() {
        isTest.set(false);
        System.out.println("solving real file for " + name());
        p1();
        p2();
    }

    default void solveTest() {
        isTest.set(true);
        System.out.println("solving test file for " + name());
        p1();
        p2();
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
