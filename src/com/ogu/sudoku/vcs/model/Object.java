package com.ogu.sudoku.vcs.model;

import java.io.Serializable;
import java.util.UUID;

public class Object implements Serializable {
    private UUID uuid;
    private UUID pointer;

    public Object(UUID pointer) {
        this.uuid = UUID.randomUUID();
        this.pointer = pointer;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getPointer() {
        return pointer;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setPointer(UUID pointer) {
        this.pointer = pointer;
    }
}
