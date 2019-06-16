package com.biblioteca;

public enum LibraryItemType {
    BOOK("book"), MOVIE("movie");

    private String text;

    LibraryItemType(String text){
        this.text = text;
    }

    public String getValue() {
        return text;
    }
}
