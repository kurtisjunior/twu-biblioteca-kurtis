package com.biblioteca;

public class Book extends LibraryItem implements Displayable {
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

    @Override
    public String display() {
        if(getCheckedStatus()) {
            return String.format("%-28s%-28s%-28s%-28s%-28s%-28s%-28s", getAuthor(),("|"), getTitle(), ("|"), getDate(), ("|"), getCheckedOutUser());
        }
        return String.format("%-28s%-28s%-28s%-28s%-28s", getAuthor(),("|"), getTitle(), ("|"), getDate());

    }
}
