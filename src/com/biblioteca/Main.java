package com.biblioteca;

public class Main {
    public static void main (String[] args) {

        Book[] booksArray = new Book[2];

        booksArray[0] = new Book("Matthew Syed", "Bounce", "2010");
        booksArray[1] = new Book("Plato", "The Republic", "308");

        //pass the library instance to the prompter
        Prompter prompter = new Prompter(booksArray);

       //display the welcome message
        prompter.welcomeMessage();

        prompter.displayBooks();
    }
}

