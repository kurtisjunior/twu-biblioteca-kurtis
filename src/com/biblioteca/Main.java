package com.biblioteca;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {

        ArrayList<Book> booksArray = new ArrayList<>();

        booksArray.add(new Book("Matthew Syed", "Bounce", "2010"));
        booksArray.add(new Book("Plato", "the Republic", "380BC"));


        Library library = new Library(booksArray);
        Prompter prompter = new Prompter(library);

        prompter.displayWelcomeMessage();

        while(!prompter.quitMenu) {
            prompter.displayMenu();
        }
    }
}
