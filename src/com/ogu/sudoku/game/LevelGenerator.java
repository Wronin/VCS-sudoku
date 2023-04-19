package com.ogu.sudoku.game;

import java.util.Arrays;
import java.util.Random;

public class LevelGenerator {
    public LevelGenerator() {
    }
    public int[][] generateLevel(int[][] level) {
        Random generator = new Random(System.currentTimeMillis());
        int[][] freeLevel = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int x, y, count;

        count = generator.nextInt(81);
        for (int i = 0; i < count; i++) {
            x = generator.nextInt(9);
            y = generator.nextInt(9);
            freeLevel[x][y] = level[x][y];
        }

        System.out.println(Arrays.deepToString(freeLevel));

        return freeLevel;
    }
}
