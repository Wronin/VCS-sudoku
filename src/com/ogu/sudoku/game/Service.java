package com.ogu.sudoku.game;

public class Service {
    public Service() {
    }

    public void moveCursor(GameScene gameScene, int x, int y) {
        int newX, newY;

        newX = gameScene.getX() + x;
        newY = gameScene.getY() + y;
        gameScene.setX(newX);
        gameScene.setY(newY);
    }

    public void writeNumberInGameScene(GameScene gameScene, int number) {
        gameScene.setGameCell(gameScene.getX(), gameScene.getY(), number);
    }

    public boolean victoryCheck(GameScene gameScene) {
        int[] uniqueNumbers = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[][] nums = gameScene.getGameScene();

        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < nums[i].length; j++) {
                if (nums[i][j] != 0)
                    uniqueNumbers[nums[i][j] - 1]++;
            }

            for (int j = 0; j < nums[i].length; j++){
                if (uniqueNumbers[j] != 1)
                    return false;
            }
            uniqueNumbers = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if (nums[j][i] != 0)
                    uniqueNumbers[nums[j][i] - 1]++;
            }
            for (int j = 0; j < nums[i].length; j++){
                if (uniqueNumbers[j] != 1) {
                    return false;
                }
            }
            uniqueNumbers = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        }

        // squares check

        for (int i = 1; i <= 3; i++){
            for (int ii = 1; ii <= 3; ii++) {
                for (int j = (i - 1) * 3; j < (i - 1) * 3 + 3; j++) {
                    for (int jj = (ii - 1) * 3; jj < (ii - 1) * 3 + 3; jj++) {
                        if (nums[i][j] != 0)
                            uniqueNumbers[nums[j][jj] - 1]++;
                    }
                }

                for (int num : uniqueNumbers) {
                    if (num != 1)
                        return false;
                }
                uniqueNumbers = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
            }
        }
        System.out.println("You Won");
        return true;
    }
}
