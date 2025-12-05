package com.prakash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day5 implements Problem {

    public Day5() {
    }

    @Override
    public void p1() {
        var input = lines().stream()
                .filter(str -> !str.isEmpty())
                .map(str -> str.split("-"))
                .map(arr -> Arrays.stream(arr)
                        .map(Long::parseLong)
                        .toArray(Long[]::new)
                ).toList();

        var ranges = input.stream()
                .filter(num -> num.length == 2)
                .toList();

        var count = input.stream()
                .filter(num -> num.length == 1)
                .map(num -> num[0])
                .filter(num -> ranges.stream()
                        .anyMatch(range -> num >= range[0] && num <= range[1])
                ).count();

        System.out.println("part 1: " + count);
    }

    @Override
    public void p2() {
        var ranges = lines().parallelStream()
                .map(str -> str.split("-"))
                .filter(split -> split.length == 2)
                .map(split -> Arrays.stream(split)
                        .map(Long::parseLong)
                        .toArray(Long[]::new))
                .sorted(Comparator.comparing(a -> a[0]))
                .toArray(Long[][]::new);

        List<Long[]> list = new ArrayList();
        for (int i = 0; i < ranges.length - 1; i++) {
            Long[] one = ranges[i];
            Long[] two = ranges[i + 1];

            if (two[0] <= one[1]) {
                one[1] = Math.max(two[1], one[1]);
                ranges[i + 1] = one;
            } else {
                list.add(one);
            }
        }
        list.add(ranges[ranges.length - 1]);

        System.out.println(
                "part 2: " +
                        list.stream()
                                .map(range -> range[1] - range[0] + 1)
                                .reduce(0L, Long::sum)
        );
    }
}
