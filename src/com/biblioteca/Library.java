package com.biblioteca;

import java.util.ArrayList;
import java.util.stream.Collectors;


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
        if(filter == "available") {
            for(Book book : booksArray) {
                if(!book.getCheckedStatus()){
                    availableBooks.add(book);
                }
            }
        }
        return availableBooks;
    }

    public boolean checkOut(int checkedIndex){
        for(Book book : booksArray){
            try{
                if(book.getIndex() == checkedIndex){
                    return book.setCheckedStatus("check out");
                }
            } catch(IllegalArgumentException iae){
                System.out.println(iae.getMessage());
                return false;
            }
        }
        throw new IllegalArgumentException("The book you have selected does not exist, please try again\n\n");
    }
}
