package com.ogu.sudoku.gui;

import com.ogu.sudoku.game.GameScene;
import com.ogu.sudoku.game.Service;
import com.ogu.sudoku.vcs.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Application extends JFrame{
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final int SQ_SIZE = 40;
    private Service service;
    private Controller controller;
    private GameScene gameScene;

    public Application(Service service, Controller controller, GameScene gameScene) {
        this.service = service;
        this.controller = controller;
        this.gameScene = gameScene;

        this.setSize(400, 420);
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> service.moveCursor(gameScene, 0, -1);
                    case KeyEvent.VK_A -> service.moveCursor(gameScene, -1, 0);
                    case KeyEvent.VK_S -> service.moveCursor(gameScene, 0, 1);
                    case KeyEvent.VK_D -> service.moveCursor(gameScene, 1, 0);
                    case KeyEvent.VK_E -> new VCSControl(service, controller, gameScene);
                }

                if (e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9) {
                    service.writeNumberInGameScene(gameScene, e.getKeyCode() - 48);
                }
                invalidate();
                repaint();
            }
        });
    }

    public void paint(Graphics g) {
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
