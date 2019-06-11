package com.biblioteca;

public class Book extends LibraryItem {
    private String author;
    private String title;
    private String date;

    public Book(String author, String title, String date) {
        super();
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

}
