package com.ogu.sudoku.gui;

import com.ogu.sudoku.game.GameScene;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePreviewPanel extends JPanel {
    public final GameScene gameScene;

    public GamePreviewPanel(GameScene gameScene) {
        this.gameScene = gameScene;
        this.setSize(420, 400);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int SQ_SIZE = 40;
        try {
            BufferedImage image;
            g.setColor(Color.lightGray);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    image = ImageIO.read(new File("C:\\Users\\Sergei\\IdeaProjects\\sudoku\\src\\com\\ogu\\sudoku\\gui\\Images\\" + gameScene.getGameScene()[i][j] + ".png"));
                    g.drawImage(image, 20 + SQ_SIZE * i, 40 + SQ_SIZE * j, SQ_SIZE, SQ_SIZE, null);
                }
            }
            g.setColor(Color.red);
            g.drawRect(20 + (SQ_SIZE * gameScene.getX()), 41 + (SQ_SIZE * gameScene.getY()), SQ_SIZE - 1, SQ_SIZE - 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
