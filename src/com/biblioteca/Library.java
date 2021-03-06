package com.biblioteca;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> booksArray;
    private ArrayList<Movie> moviesArray;
    private ArrayList<User> userArray;
    private ArrayList<User> loggedInUser = new ArrayList<>();

    public Library(ArrayList<Book> booksArray, ArrayList<Movie> moviesArray, ArrayList<User> userArray){
        this.booksArray = booksArray;
        this.moviesArray = moviesArray;
        this.userArray = userArray;
    }

    ArrayList<Book> getBooks(String filter) {
        ArrayList<Book> availableBooks = new ArrayList<>();
        if(filter.equals("available")) {
            for(Book book : booksArray) {
                if(!book.getCheckedStatus()){
                    availableBooks.add(book);
                }
            }
        } else if(filter.equals("checked")){
            for(Book book : booksArray) {
                if(book.getCheckedStatus()){
                    availableBooks.add(book);
                }
            }
        }
        return availableBooks;
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

    public boolean bookAction(String checkedInput, String action, String libraryNumber){
        //check if book id equals user id if not return false
        for(Book book : booksArray){
            String formatInput = checkedInput.toLowerCase();
            String formatTitle = book.getTitle().toLowerCase();

            Boolean titleMatch = formatInput.equals(formatTitle);
            Boolean bookMatch = libraryNumber.equals(book.getCheckedOutUser());

            try{
                if(titleMatch && action.equals("check out")) {
                    book.checkOut();
                    book.setCheckedOutUser(libraryNumber);
                    return true;
                } else if (titleMatch && bookMatch && action.equals("check in")) {
                    book.checkIn();
                    book.setCheckedOutUser("");
                    return true;
                }
            } catch (IllegalArgumentException iae){
                System.out.println(iae.getMessage());
                return false;
            }
        }
        throw new IllegalArgumentException("The book you have selected does not exist, please try again\n\n");
    }

    public boolean movieAction(String checkedInput, String action){
        for (Movie movie : moviesArray){
            String formatInput = checkedInput.toLowerCase();
            String formatTitle = movie.getName().toLowerCase();
            Boolean match = formatInput.equals(formatTitle);
            try{
                if(match && action.equals("check out")){
                    return movie.checkOut();
                } else if (match && action.equals("check in")) {
                    return movie.checkIn();
                }
            } catch (IllegalArgumentException iae){
                System.out.println(iae.getMessage());
                return false;
            }
        }
        throw new IllegalArgumentException("The movie you have selected does not exist, please try again\n\n");
    }

    public Boolean userLogIn(String libraryNumber, String password){
        for(User user : userArray){
            if(user.getLibraryNumber().equals(libraryNumber)){
                if(user.getPassword().equals(password)) {
                    loggedInUser.add(user);
                    return true;
                }
            }
        }
        throw new IllegalArgumentException("\nlogin failed, incorrect library number or password\n");
    }

    public ArrayList getLoggedInUser(){
        return this.loggedInUser;
    }

    public void clearLoggedInUser(){
        loggedInUser = new ArrayList<>();
    }

    public void checkOutItem(LibraryItemType type, String libraryItemTitle, LibraryItemAction action, String libraryNumber) {
        if (type == LibraryItemType.BOOK) {
            bookAction(libraryItemTitle, action.getValue(), libraryNumber);
        }
        else if (type == LibraryItemType.MOVIE) {
            movieAction(libraryItemTitle, action.getValue());
        }
    }

}

