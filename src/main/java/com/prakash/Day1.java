package com.prakash;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Day1 implements Problem {

    @Override
    public void p1() {
        AtomicLong result = new AtomicLong(0L);
        AtomicInteger start = new AtomicInteger(50);
        lines().stream()
                .map(line -> new String[]{line.substring(0, 1), line.substring(1)})
                .forEach(line -> {
                    var amount = Integer.parseInt(line[1]);
                    amount %= 100;
                    var newPos = line[0].equals("L") ? start.get() - amount : start.get() + amount;
                    if (newPos < 0 && start.get() != 0) {
                        newPos += 100;
                    }
                    if (newPos > 100 && start.get() != 0) {
                        newPos -= 100;
                    }
                    if (newPos == 0 || newPos == 100) {
                        newPos = 0;
                        result.getAndIncrement();
                    }
                    start.set(newPos);
                });

        System.out.println("part 1: " + result.get());
    }

    @Override
    public void p2() {
        AtomicLong result = new AtomicLong(0L);
        AtomicInteger start = new AtomicInteger(50);

        lines().stream()
                .map(line -> new String[]{line.substring(0, 1), line.substring(1)})
                .forEach(line -> {
                    var amount = Integer.parseInt(line[1]);
                    result.addAndGet(amount / 100);
                    amount %= 100;
                    var newPos = line[0].equals("L") ? start.get() - amount : start.get() + amount;
                    if (newPos < 0) {
                        newPos += 100;
                        if (start.get() != 0) result.incrementAndGet();
                    }
                    if (newPos > 100) {
                        newPos -= 100;
                        if (start.get() != 0) result.incrementAndGet();
                    }
                    if (newPos == 0 || newPos == 100) {
                        newPos = 0;
                        if (start.get() != 0) result.incrementAndGet();
                    }
                    start.set(newPos);
                });

        System.out.println("part 2: " + result.get());
    }
}
