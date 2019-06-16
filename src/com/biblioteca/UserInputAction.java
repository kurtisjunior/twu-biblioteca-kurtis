package com.biblioteca;

public enum UserInputAction {
    LIBRARY_NUMBER("library number"),
    PASSWORD("password"),
    TITLE("title");

    private String text;

    UserInputAction(String text){
        this.text = text;
    }

    public String getValue(){
        return text;
    }
}
