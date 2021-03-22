package com.epam.esm.dao;

public enum SortEnum {
    ASC(true),
    DESC(false);

    private final boolean value;

    SortEnum(boolean value) {
        this.value = new Boolean(value);
    }

    public boolean getValue() {
        return value;
    }
}
