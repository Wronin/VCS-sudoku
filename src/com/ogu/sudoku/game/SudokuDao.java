package com.ogu.sudoku.game;

import java.util.ArrayList;

public class SudokuDao {
    private ArrayList<GameScene> gameScenes = new ArrayList<>();

    public void addGameScene(GameScene gameScene) {
        gameScenes.add(gameScene);
    }
    public GameScene getGameSceneById(int id) {
        return gameScenes.get(id);
    }
    public void setGameScenes(ArrayList<GameScene> gameScenes) {
        this.gameScenes = gameScenes;
    }
    public ArrayList<GameScene> getGameScenes() {
        return gameScenes;
    }
}
