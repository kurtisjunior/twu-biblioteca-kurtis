package com.biblioteca;

public class Book {
    private static int instanceCounter = 1;
    private int index;

    private String author;
    private String title;
    private String date;
    private boolean checkedOut = false;

    public Book(String author, String title, String date) {
        index = instanceCounter;
        instanceCounter += 1;

        this.author = author;
        this.title = title;
        this.date = date;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDate() {
        return this.date;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean getCheckedStatus() {
        return checkedOut;
    }

    public boolean setCheckedStatus(String action) {
        if (action == "check out" && checkedOut == false) {
            return checkedOut = true;
        }
        throw new IllegalArgumentException("Sorry, that book is not available\n");
    }
}
