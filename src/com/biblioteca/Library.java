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

    public boolean checkOut(String checkedInput){
        for(Book book : booksArray){
            try{
                String formatInput = toLowerCase(checkedInput);
                String formatTitle = toLowerCase(book.getTitle());

                if(formatInput.equals(formatTitle)){
                    return book.setCheckedOut();
                }
            } catch(IllegalArgumentException iae){
                System.out.println(iae.getMessage());
                return false;
            }
        }
        throw new IllegalArgumentException("The book you have selected does not exist, please try again\n\n");
    }

    public boolean checkIn(String checkedInput){
        for(Book book : booksArray){
            String formatInput = toLowerCase(checkedInput);
            String formatTitle = toLowerCase(book.getTitle());

            if(formatInput.equals(formatTitle)){
                return book.setCheckedIn();
            }
        }
        throw new IllegalArgumentException("The book you have selected does not exist, please try again\n\n");
    }

    public String toLowerCase(String checkedInput){
        String lowerCase = "";
        for(char letter : checkedInput.toCharArray()){
            if(Character.isUpperCase(letter)) {
                lowerCase += Character.toLowerCase(letter);
            } else {
                lowerCase += letter;
            }
        }
        return lowerCase;
    }
}

/*
FIX:
missing check in try block
 */