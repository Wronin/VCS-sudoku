package com.ogu.sudoku.vcs.model;

import java.util.UUID;

public class Branch extends Object{
    private String name;
    public Branch (String name, UUID pointer) {
        super(pointer);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
