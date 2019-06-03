package com.biblioteca;

public class Main {
    public static void main (String[] args) {

        Book[] booksArray = new Book[2];
        booksArray[0] = new Book("Matthew Syed", "Bounce", "2010");
        booksArray[1] = new Book("Plato", "The Republic", "380BC");


        Library library = new Library(booksArray);
        Prompter prompter = new Prompter(library);

        prompter.displayWelcomeMessage();

        while(!prompter.quitMenu) {
            prompter.displayMenu();
        }

    }
}
