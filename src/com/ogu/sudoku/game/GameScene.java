package com.ogu.sudoku.game;

public class GameScene {
    int[][] gameScene;
    int x, y;

    public GameScene(int[][] gameScene, int x, int y) {
        this.gameScene = gameScene;
        this.x = x;
        this.y = y;
    }

    public int[][] getGameScene() {
        return gameScene;
    }

    public void setGameScene(int[][] gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameCell(int x, int y, int number) {
        this.gameScene[x][y] = number;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
