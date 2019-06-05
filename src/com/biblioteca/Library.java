package com.biblioteca;

import java.util.ArrayList;


public class Library {

    private ArrayList<Book> booksArray;

    public Library(ArrayList<Book> booksArray){
        this.booksArray = booksArray;
    }

    public ArrayList<Book> getBooks(){
        return this.booksArray;
    }

    public ArrayList<Book> getBooks(String filter) {
        ArrayList<Book> availableBooks = new ArrayList<>();
        if(filter.equals("available")) {
            for(Book book : booksArray) {
                if(!book.getCheckedStatus()){
                    availableBooks.add(book);
                }
            }
        }
        return availableBooks;
    }

    public boolean checkOut(String checkedIndex){
        for(Book book : booksArray){
            try{
                //format function here to compare two lower case strings

                if(book.getTitle().equals(checkedIndex)){
                    return book.setCheckedOut();
                }
            } catch(IllegalArgumentException iae){
                System.out.println(iae.getMessage());
                return false;
            }
        }
        throw new IllegalArgumentException("The book you have selected does not exist, please try again\n\n");
    }

    public boolean checkIn(String checkedIndex){
        for(Book book : booksArray){
            if(book.getTitle().equals(checkedIndex)){
                return book.setCheckedIn();
            }
        }
        throw new IllegalArgumentException("The book you have selected does not exist, please try again\n\n");
    }
}
