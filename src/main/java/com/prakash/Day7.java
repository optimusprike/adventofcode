package com.prakash;

public class Day7 implements Problem {

    @Override
    public void p1() {
        var grid = lines().stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        traverse(grid);
    }

    @Override
    public void p2() {
        var grid = lines().stream()
                .map(String::toCharArray)
                .map(this::convert)
                .toArray(long[][]::new);

         traverse(grid);
    }

    private void traverse(char[][] grid) {
        long total = 0L;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '.') {
                    if (i > 0 && grid[i - 1][j] == 'X') {
                        grid[i][j] = 'X';
                    }
                }
                if (grid[i][j] == 'S') {
                    if (grid[i + 1][j] != '^') grid[i + 1][j] = 'X';
                }
                if (grid[i][j] == 'X') {
                    if (i < grid.length -1 && grid[i + 1][j] != '^') grid[i + 1][j] = 'X';
                }
                if (grid[i][j] == '^') {
                    if (grid[i - 1][j] == 'X') {
                        total++;
                        if (grid[i][j - 1] != '^') grid[i][j - 1] = 'X';
                        if (grid[i][j + 1] != '^') grid[i][j + 1] = 'X';
                    }
                }
            }
        }

        System.out.println("part 1: " + total);
    }

    private long[] convert(char[] arr) {
        long[] res = new long[arr.length];

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '.') res[i] = 0;
            if(arr[i] == 'S') res[i] = -2;
            if(arr[i] == '^') res[i] = -1;
        }

        return res;
    }

    private void traverse(long[][] grid) {
        long total = 0L;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) { // .
                    if (i > 0 && grid[i - 1][j] >0) {
                        grid[i][j] += grid[i-1][j];
                    }
                }
                if (grid[i][j] == 0) { // .
                    if (i > 0 && grid[i - 1][j] >0) {
                        grid[i][j] = grid[i-1][j];
                    }
                }
                if (grid[i][j] == -2) { // S
                    if (grid[i + 1][j] != -1) grid[i + 1][j]++;
                }
                if (grid[i][j] == -1) { // ^
                    if (grid[i - 1][j] > 0) {
                        if (grid[i][j - 1] != -1) grid[i][j - 1] += Math.max(1, grid[i - 1][j]);
                        if (grid[i][j + 1] != -1) grid[i][j + 1]+= Math.max(1, grid[i - 1][j]);
                    }
                }
            }
        }

        for(int i = 0; i < grid[0].length; i++) {
            total += grid[grid.length -1][i];
        }

        System.out.println("part 2: " + total);
    }
}
