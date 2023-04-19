package com.ogu.sudoku.vcs.model;

import com.ogu.sudoku.game.GameScene;

import java.util.ArrayList;
import java.util.UUID;

public class Commit extends Object{
    private int[][] level;

    public Commit(int[][] level, UUID pointer) {
        super(pointer);
        this.level = level;
    }

    public void setLevel(int[][] level) {
        this.level = level;
    }

    public int[][] getLevel() {
        return level;
    }
}
