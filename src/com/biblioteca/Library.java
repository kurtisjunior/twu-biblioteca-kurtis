package com.biblioteca;

import java.util.ArrayList;


public class Library {

    private ArrayList<Book> booksArray;
    private ArrayList<Movie> moviesArray;

    public Library(ArrayList<Book> booksArray, ArrayList<Movie> moviesArray){
        this.booksArray = booksArray;
        this.moviesArray = moviesArray;
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

    public ArrayList<Movie> getMovies(){
        return this.moviesArray;
    }

    public ArrayList<Movie> getMovies(String filter) {
        ArrayList<Movie> availableMovies = new ArrayList<>();
        if(filter.equals("available")) {
            for(Movie movie: moviesArray) {
                if(!movie.getCheckedStatus()){
                    availableMovies.add(movie);
                }
            }
        }
        return availableMovies;
    }

    public boolean checkOutBook(String checkedInput){
        for(Book book : booksArray){
            try{
                String formatInput = toLowerCase(checkedInput);
                String formatTitle = toLowerCase(book.getTitle());

                if(formatInput.equals(formatTitle)){
                    return book.checkOut();
                }
            } catch(IllegalArgumentException iae){
                System.out.println(iae.getMessage());
                return false;
            }
        }
        throw new IllegalArgumentException("\nThe book you have selected does not exist, please try again\n\n");
    }

    public boolean checkInBook(String checkedInput){
        for(Book book : booksArray){
            try {
                String formatInput = toLowerCase(checkedInput);
                String formatTitle = toLowerCase(book.getTitle());

                if(formatInput.equals(formatTitle)){
                    return book.checkIn();
                }

            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                return false;
            }
        }
        throw new IllegalArgumentException("The book you have selected does not exist, please try again\n\n");
    }

    public boolean checkOutMovie(String checkedInput){
        for(Movie movie : moviesArray) {
            try{
                String formatInput = toLowerCase(checkedInput);
                String formatTitle = toLowerCase(movie.getName());

                if(formatInput.equals(formatTitle)){
                    return movie.checkOut();
                }

            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                return false;
            }
        }
        throw new IllegalArgumentException("The movie you have selected does not exist, please try again\n\n");

    }

    public boolean checkInMovie(String checkedInput){
        for(Movie movie : moviesArray){
            try{
                String formatInput = toLowerCase(checkedInput);
                String formatTitle = toLowerCase(movie.getName());

                if(formatInput.equals(formatTitle)){
                    return movie.checkIn();
                }
            } catch(IllegalArgumentException iae){
                System.out.println(iae.getMessage());
                return false;
            }
        }
        throw new IllegalArgumentException("The movie you have selected does not exist");
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
