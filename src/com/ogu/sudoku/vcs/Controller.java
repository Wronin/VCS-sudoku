package com.ogu.sudoku.vcs;

import com.ogu.sudoku.game.GameScene;
import com.ogu.sudoku.vcs.model.Branch;
import com.ogu.sudoku.vcs.model.Commit;
import com.ogu.sudoku.vcs.model.Object;

import java.io.Serializable;
import java.util.*;

public class Controller implements Serializable {
    private final Map<UUID, Commit> commitMap = new HashMap<>();
    private final Map<String, Branch> branchMap = new HashMap<>();
    private Object head;

    public void createMain() {
        head = new Branch("Main", null);
        addBrunch("Main");
    }
    public void addCommit(GameScene gameScene) {
        int[][] level = gameScene.getGameScene().clone();
        int[][] levelForClone = new int[9][];

        for (int i = 0; i < 9; i++) {
            levelForClone[i] = level[i].clone();
        }

        Commit commit = null;

        if (getBranchByPointer(head.getPointer()).getPointer() == null) {
            commit = new Commit(levelForClone, null);
        } else {
            commit = new Commit(levelForClone, getBranchByPointer(head.getPointer()).getPointer());
        }

        setBranchPointerOnCommit(commit);
        commitMap.put(commit.getUuid(), commit);
        head.setPointer(getBranchFromCommit(commit).getUuid());
    }

    public void addBrunch(String name) {
        Branch branch = null;
        if (head.getPointer() == null) {
            branch = new Branch(name, null);
        } else {
            branch = new Branch(name, getUuidFromCurrentBranch(head.getPointer()));
            ((Branch) head).setName(name);
        }

        head.setPointer(branch.getUuid());
        branchMap.put(name, branch);
    }

    public void createCommitAndBranch(GameScene gameScene, String commitUuid, String branchName) {
        Branch branch = branchMap.get(branchName);
        if (branch.getPointer().equals(UUID.fromString(commitUuid))) {
            addCommit(gameScene);
        } else {
            branchName += "1";
            addBrunch(branchName);
            addCommit(gameScene);
        }
    }
    public ArrayList<String> getBranchesNameList() {
        ArrayList<String> branchNameList = new ArrayList<>();
        for(var branch : branchMap.entrySet()) {
            branchNameList.add(branch.getKey());
        }
        return branchNameList;
    }

    public Commit getCommit(UUID uuid) {
        return commitMap.get(uuid);
    }
    public ArrayList<String> getCommitsByNameBranch(String name) {
        ArrayList<String> commitList =  new ArrayList<>();
        Branch br = null;
        Commit co = null;
        br = branchMap.get(name);
//        co = commitMap.get(br.getPointer());

        for (var commit : commitMap.entrySet()) {
            if (commit.getKey().equals(br.getPointer())) {
                co = commit.getValue();
            }
        }

        while (true) {
            commitList.add(co.getUuid().toString());
            if (co.getPointer() == null) {
                break;
            }
            co = commitMap.get(co.getPointer());
        }


        return commitList;
    }

    public void setBranchPointerOnCommit(Commit commit) {
        Branch branch = new Branch(((Branch) head).getName(), commit.getUuid());
        branchMap.computeIfPresent(((Branch) head).getName(), (k, v) -> branch);
    }
    public Branch getBranchFromCommit(Commit commit) {
        Branch branch = null;
        for (var br : branchMap.entrySet()) {
            if (br.getValue().getPointer().equals(commit.getUuid())) {
                branch = br.getValue();
            }
        }
        return branch;
    }

    public Branch getBranchByPointer(UUID uuid) {
        for (var branch : branchMap.entrySet()) {
            if (branch.getValue().getUuid().equals(uuid))
                return branch.getValue();
        }
        return new Branch(null, null);
    }
    public UUID getUuidFromCurrentBranch(UUID uuid) {
        UUID id = null;

        for (var branch : branchMap.entrySet()) {
            if (branch.getValue().getUuid() == uuid) {
                return branch.getValue().getPointer();
            }
        }

        return id;
    }

}
