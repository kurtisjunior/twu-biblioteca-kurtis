package com.biblioteca;

public enum LibraryItemAction {
    CHECK_IN("check in"),
    CHECK_OUT ("check out");

    private String text;

    LibraryItemAction(String text) {
        this.text = text;
    }

    public String getValue() {
        return text;
    }
}

