package com.biblioteca;

public class Book {
    private String author;
    private String title;
    private String date;
    private boolean checkedOut = false;

    public Book(String author, String title, String date) {
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

    public boolean getCheckedStatus() {
        return this.checkedOut;
    }

    public boolean setCheckedOut() {
        if (!checkedOut) {
            return checkedOut = true;
        }
        throw new IllegalArgumentException("\nSorry, that book is not available\n");
    }

    public boolean setCheckedIn() {
        if (checkedOut) {
            checkedOut = false;
            return true;
        }
        throw new IllegalArgumentException("\nSorry, that book is already checked in\n");
    }

}
