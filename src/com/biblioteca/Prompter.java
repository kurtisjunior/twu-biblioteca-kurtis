package com.biblioteca;

import java.util.ArrayList;

import java.util.Scanner;

public class Prompter {
    private Library library;
    public boolean quitMenu = false;

    public Prompter(Library library) {
        this.library = library;
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore ! \n\n");
    }

    public void userInput() {
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
        userInput();
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
        System.out.printf("%67s\n\n", "------ Available books ------");
        System.out.printf("%-22s%-22s%-22s%-22s%-22s\n","*** Author ***", (""), "*** Title ***", (""), "*** Published ***");
        ArrayList<Book> booksArray = library.getBooks("available");
        for(Book book : booksArray){
            System.out.printf("%-22s%-22s%-22s%-22s%-22s\n", book.getAuthor(),("|"),book.getTitle(), ("|"), book.getDate());
        }
        System.out.println("\n");
    }

    public void checkOutBook(){
        boolean acceptableInput = false;
        boolean checkOutResult = false;
        while(!acceptableInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the book title to check out: ");
                String input = (scanner.nextLine());
                try {
                   checkOutResult = library.checkOut(input);
                   acceptableInput = true;
                } catch (IllegalArgumentException iae){
                    System.out.println(iae.getMessage());
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid input");
            }
            if(checkOutResult){
                System.out.println("\nSuccessfully checked out. Thank you ! Enjoy the book \n");
                displayMenu();
            } else {
                displayMenu();
            }
        }
    }

    public void checkInBook(){
        boolean acceptableInput = false;
        boolean checkInResult = false;
        while(!acceptableInput){
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the book title to return: ");
                String input = (scanner.nextLine());
                try{
                    checkInResult = library.checkIn(input);
                    acceptableInput = true;

                } catch (IllegalArgumentException iae){
                    System.out.println(iae.getMessage());
                }
            } catch (Exception e){
                System.out.println("Please enter a valid input");
            }
            if(checkInResult){
                System.out.println("\nSuccessfully returned. Thank you !\n ");
                displayMenu();
            } else {
                displayMenu();
            }
        }
    }
}
