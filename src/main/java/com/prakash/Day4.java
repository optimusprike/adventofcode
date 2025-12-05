package com.prakash;

import java.util.ArrayList;
import java.util.List;

public class Day4 implements Problem {

    public Day4() {
    }

    @Override
    public void p1(String fileName) {
        var grid = read(fileName)
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int r = grid.length;
        int c = grid[0].length;
        long count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (eval(i, j, r, c, grid)) count++;
            }
        }

        System.out.println("part 1: " + count);
    }

    @Override
    public void p2(String fileName) {
        var grid = read(fileName)
                .stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int r = grid.length;
        int c = grid[0].length;
        long count = 0;
        long x = -1;
        List<int[]> list = new ArrayList<>();
        while (x != 0) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    var eval = check(i, j, r, c, grid);
                    if (eval[0] != -1) {
                        list.add(eval);
                    }
                }
            }
            x = list.size();
            count += list.size();

            for (int[] point : list) {
                grid[point[0]][point[1]] = '.';
            }

            list.clear();
        }

        System.out.println("part 2: " + count);

    }

    private boolean eval(int i, int j, int r, int c, char[][] grid) {
        if (i < 0 || i >= r || j < 0 || j >= c || grid[i][j] == '.') return false;
        int count = 0;
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int d = 0; d < 8; d++) {
            var ni = i + dr[d];
            var nj = j + dc[d];
            if (ni >= 0 && ni < r && nj >= 0 && nj < c && grid[ni][nj] == '@') {
                count++;
            }
        }
        return count < 4;
    }

    private int[] check(int i, int j, int r, int c, char[][] grid) {
        return eval(i, j, r, c, grid) ? new int[]{i, j} : new int[]{-1, -1};
    }
}
