package com.epam.esm.model;

import java.io.Serializable;

public class Tag extends Entity implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag() {
    }

}
