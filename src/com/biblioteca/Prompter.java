package com.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Prompter {
    private Library library;
    public boolean quitMenu = false;

    public Prompter(Library library) {
        this.library = library;
    }

    public void start(){
        displayWelcomeMessage();
        while(!quitMenu) {
            displayMenu();
        }
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore ! \n\n");
    }

    public void menuInput() {
        boolean acceptableInput = false;
        while (!acceptableInput) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose an option: ");
            try {
                int input = Integer.parseInt(scanner.nextLine());
                handleMenu(input);
                acceptableInput = true;
            } catch (IllegalArgumentException iae) {
                System.out.println("Please enter a valid menu option");
            }
        }
    }

    public void displayMenu() {
        System.out.println("Menu options: ");
        System.out.println("1. List of books");
        System.out.println("2. Check out book");
        System.out.println("3. Return book");
        System.out.println("4. Quit\n");
        menuInput();
    }

    public void handleMenu(int input) {
        switch (input) {
            case 1:
                displayBooks();
                break;
            case 2:
                checkOutBook();
                break;
            case 3:
                checkInBook();
                break;
            case 4:
                quitMenu = true;
                System.out.println("\nThanks for using Biblioteca !");
                System.exit(0);
            default:
                System.out.println("Please select a valid option");
        }
}

    public void displayBooks(){
        System.out.printf("%80s\n\n", "-------- Available books --------");
        System.out.printf("%-28s%-28s%-28s%-28s%-28s\n","*** Author ***", (""), "*** Title ***", (""), "*** Published ***");
        ArrayList<Book> booksArray = library.getBooks("available");
        for(Book book : booksArray){
            System.out.printf("%-28s%-28s%-28s%-28s%-28s\n", book.getAuthor(),("|"),book.getTitle(), ("|"), book.getDate());
        }
        System.out.println("\n");
    }


    public String bookInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the book title: ");
        String input = (scanner.nextLine());
        return input;
    }


    public void checkOutBook() {
        boolean success = false;
        String input = bookInput();
        try {
            success = library.checkOut(input);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        if(success){
            System.out.println("\nSuccessfully checked out. Thank you ! Enjoy the book \n");
        }
    }


    public void checkInBook() {
        boolean success = false;
        String input = bookInput();
        try {
            success = library.checkIn(input);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        if(success) {
            System.out.println("\nSuccessfully returned. Thank you !\n ");
        }
    }
}
