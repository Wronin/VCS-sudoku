import com.ogu.sudoku.game.GameScene;
import com.ogu.sudoku.game.LevelGenerator;
import com.ogu.sudoku.game.Service;
import com.ogu.sudoku.gui.Application;
import com.ogu.sudoku.vcs.Controller;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[][] level = {
                {3, 6, 2, 5, 8, 4, 9, 1, 7}, //{3, 6, 2, 5, 8, 4, 9, 1, 7}
                {5, 0, 7, 2, 1, 9, 3, 6, 8}, //{5, 4, 7, 2, 1, 9, 3, 6, 8}
                {8, 9, 0, 7, 6, 3, 2, 4, 5}, //{8, 9, 1, 7, 6, 3, 2, 4, 5}
                {2, 7, 8, 6, 4, 5, 1, 3, 9},
                {1, 5, 9, 3, 2, 7, 4, 8, 6},
                {6, 3, 4, 8, 9, 1, 5, 7, 2},
                {7, 8, 5, 1, 3, 2, 6, 9, 4},
                {4, 1, 6, 9, 5, 8, 7, 2, 3},
                {9, 2, 3, 4, 7, 6, 8, 5, 1},
        };

        Controller controller = new Controller();
        Service service;
        GameScene gameLevel;

        String filePath = "C:\\Users\\Sergei\\IdeaProjects\\sudoku\\src\\ControllerVCS.txt";

//        try {
//            FileInputStream fileInputStream = new FileInputStream(filePath);
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            controller = (Controller) objectInputStream.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        service = new Service();
        gameLevel = new GameScene(level, 0, 0);

        controller.createMain();
        controller.addCommit(gameLevel);
//
        service.moveCursor(gameLevel, 1, 1);
        service.writeNumberInGameScene(gameLevel, 4);
        controller.addCommit(gameLevel);
//
        controller.addBrunch("test");
        service.moveCursor(gameLevel, 1, 1);
        service.writeNumberInGameScene(gameLevel, 1);
        controller.addCommit(gameLevel);
        service.moveCursor(gameLevel, 1, 0);
        service.writeNumberInGameScene(gameLevel, 1);
        service.moveCursor(gameLevel, 1, 0);
        service.writeNumberInGameScene(gameLevel, 1);
        service.moveCursor(gameLevel, 1, 0);
        service.writeNumberInGameScene(gameLevel, 1);
        controller.addCommit(gameLevel);
//

        ArrayList<String> branchList = new ArrayList<>();
        ArrayList<String> commitList = new ArrayList<>();
        branchList = controller.getBranchesNameList();
        commitList = controller.getCommitsByNameBranch("Main");

//        LevelGenerator levelGenerator = new LevelGenerator();
//        GameScene gameLevel;

//        gameLevel = new GameScene(levelGenerator.GenerateLevel(level), 0, 0);

//        while (!(service.victoryCheck(gameLevel))) {
//            service.moveCursor(gameLevel, 1, 1);
//            service.writeNumberInGameScene(gameLevel, 4);
//        }

        Application application = new Application(service, controller, gameLevel);
        application.setVisible(true);

//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(controller);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}