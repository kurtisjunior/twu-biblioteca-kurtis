package com.biblioteca;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {

        ArrayList<Book> bookArray = new ArrayList<>();
        bookArray.add(new Book("Matthew Syed", "Bounce", "2010"));
        bookArray.add(new Book("Plato", "The Republic", "380BC"));
        bookArray.add(new Book("Tim Ferriss", "Tribe of Mentors", "2017"));
        bookArray.add(new Book("Peter F. Ducker", "The Effective Executive", "1967"));

        ArrayList<Movie> movieArray = new ArrayList<>();
        movieArray.add(new Movie("Whiplash", "2014", "Damien Chazelle", "8.5"));
        movieArray.add(new Movie("Blade Runner", "1982", "Ridley Scott", "8.2"));

        ArrayList<User> userArray = new ArrayList<>();
        userArray.add(new User("0", "k"));

        Library library = new Library(bookArray, movieArray, userArray);
        Prompter prompter = new Prompter(library);

        prompter.start();

    }
}



/*
Book
checkedUser = ""

Library
checkedBooks method
    Iterates through books
    builds an array of checked books  -----> uses the method already made
    displays checked books




 */