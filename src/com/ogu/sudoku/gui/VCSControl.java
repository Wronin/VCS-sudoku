package com.ogu.sudoku.gui;

import com.ogu.sudoku.game.GameScene;
import com.ogu.sudoku.game.Service;
import com.ogu.sudoku.vcs.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class VCSControl extends JFrame {
    private String branchName;
    private String comboboxItem;
    private JComboBox branchesComboBox;
    private JComboBox commitsComboBox;

    private Service service;
    private Controller controller;
    private GameScene gameScene;


    public VCSControl(Service service, Controller controller, GameScene gameScene) throws HeadlessException {
        this.service = service;
        this.controller = controller;
        this.gameScene = gameScene;

        this.setSize(400, 420);

        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        branchesComboBox = new JComboBox(controller.getBranchesNameList().toArray(new String[0]));
        branchesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                branchName = (String) branchesComboBox.getSelectedItem();
                ArrayList<String> list = controller.getCommitsByNameBranch(branchName);
                commitsComboBox.setModel(new DefaultComboBoxModel(list.toArray()));
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        this.add(branchesComboBox, c);

        ArrayList<String> commitList = controller.getCommitsByNameBranch(branchesComboBox.getSelectedItem().toString());
        commitsComboBox = new JComboBox<>(commitList.toArray(new String[0]));
        c.gridx = 1;
        c.gridy = 0;
        commitsComboBox.invalidate();
        this.add(commitsComboBox, c);

//        JPanel panel = new GamePreviewPanel(gameScene);
//        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
//        this.add(panel);
        JTextField textFieldBrunch = new JTextField(10);
        c.gridx = 0;
        c.gridy = 1;
        this.add(textFieldBrunch, c);

        JButton createNewBrunch = new JButton("Create new branch");
        c.gridx = 1;
        c.gridy = 1;
        createNewBrunch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addBrunch(textFieldBrunch.getText());
            }
        });
        this.add(createNewBrunch, c);

        JButton addNewCommit = new JButton("Create new commit");
        c.gridx = 2;
        c.gridy = 1;
        addNewCommit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboboxItem = commitsComboBox.getSelectedItem().toString();
                branchName = (String) branchesComboBox.getSelectedItem();
                controller.createCommitAndBranch(gameScene, comboboxItem, branchName);
                ArrayList<String> list = controller.getCommitsByNameBranch(branchName);
                commitsComboBox.setModel(new DefaultComboBoxModel(list.toArray()));
                branchesComboBox.setModel(new DefaultComboBoxModel(controller.getBranchesNameList().toArray(new String[0])));
            }
        });
        this.add(addNewCommit);

        JButton loadCommit = new JButton("Load commit");
        c.gridx = 2;
        c.gridy = 2;
        loadCommit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uuid = commitsComboBox.getSelectedItem().toString();
                gameScene.setGameScene(controller.getCommit(UUID.fromString(uuid)).getLevel());
                invalidate();
                repaint();
            }
        });
        this.add(loadCommit);

        this.pack();
        this.setVisible(true);

        // сериализация обекта
//        String filePath = "C:\\Users\\Sergei\\IdeaProjects\\sudoku\\src\\ControllerVCS.txt";
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(controller);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
