package com.prakash;

import io.vavr.control.Try;

import java.util.Arrays;
import java.util.List;

public class Day3 {
    private long p1_result;
    private long p2_result;
    private long p2v2_result;

    public Day3() {
        this.p1_result = 0L;
        this.p2_result = 0L;
        this.p2v2_result = 0L;
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

        list.forEach(this::p1);
        System.out.println("part 1: " + p1_result);
        list.forEach(this::p2);
        System.out.println("part 2: " + p2_result);
//        list.forEach(this::p2v2);
        p2v2(list);
        System.out.println("p2v2: " + p2v2_result);
        p1v2(list);
    }

    public void p1(String line) {
        int max = 0;
        var chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int a = chars[i] - '0';
            for (int j = i + 1; j < chars.length; j++) {
                int res = a * 10 + (chars[j] - '0');
                max = Math.max(max, res);
            }
        }
        this.p1_result += max;
    }

    public void p1v2(List<String> lines) {
        System.out.println(
                "p1v2: " +
                        lines.stream().map(line -> {
                            long max = 0;
                            var chars = line.toCharArray();
                            for (int i = 0; i < chars.length; i++) {
                                int a = chars[i] - '0';
                                for (int j = i + 1; j < chars.length; j++) {
                                    int res = a * 10 + (chars[j] - '0');
                                    max = Math.max(max, res);
                                }
                            }
                            return max;
                        }).reduce(0L, Long::sum)
        );
    }

    public void p2(String line) {
        int[] nums = new int[12];
        int idx = -1;

        var chars = line.chars().map(c -> c - '0').toArray();

//        for(int a = 0; a < 12; a++) {
//            for(int i = idx + 1; i < chars.length - (12 - a); i++) {
//                if(chars[i] > nums[a]) {
//                    nums[a] = chars[i];
//                    idx = i;
//                }
//            }
//        }

        for (int i = 0; i < chars.length - 11; i++) {
            if (chars[i] > nums[0]) {
                nums[0] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length - 10; i++) {
            if (chars[i] > nums[1]) {
                nums[1] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length - 9; i++) {
            if (chars[i] > nums[2]) {
                nums[2] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length - 8; i++) {
            if (chars[i] > nums[3]) {
                nums[3] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length - 7; i++) {
            if (chars[i] > nums[4]) {
                nums[4] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length - 6; i++) {
            if (chars[i] > nums[5]) {
                nums[5] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length - 5; i++) {
            if (chars[i] > nums[6]) {
                nums[6] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length - 4; i++) {
            if (chars[i] > nums[7]) {
                nums[7] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length - 3; i++) {
            if (chars[i] > nums[8]) {
                nums[8] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length - 2; i++) {
            if (chars[i] > nums[9]) {
                nums[9] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length - 1; i++) {
            if (chars[i] > nums[10]) {
                nums[10] = chars[i];
                idx = i;
            }
        }

        for (int i = idx + 1; i < chars.length; i++) {
            if (chars[i] > nums[11]) {
                nums[11] = chars[i];
                idx = i;
            }
        }

        long result = 0;
        for (int i = 0; i < 12; i++) {
            // System.out.print(nums[i]);
            result = (result * 10) + nums[i];
        }
        // System.out.println("\n");
        p2_result += result;
    }

    public void p2v2(List<String> lines) {
        var res = lines.stream().map(line -> {
            int[] nums = new int[12];
            int idx = -1;
            var chars = line.chars().map(c -> c - '0').toArray();

            for (int a = 0; a < 12; a++) {
                for (int i = idx + 1; i < chars.length - (11 - a); i++) {
                    if (chars[i] > nums[a]) {
                        nums[a] = chars[i];
                        idx = i;
                    }
                }
            }

            long result = 0;
            for (int i = 0; i < 12; i++) {
                result = (result * 10) + nums[i];
            }
            return result;
        }).reduce(0L, Long::sum);

        System.out.println("sum: " + res);
    }
}
