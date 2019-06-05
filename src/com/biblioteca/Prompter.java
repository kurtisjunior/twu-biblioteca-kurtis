package com.biblioteca;

import java.util.ArrayList;

import java.util.Scanner;

public class Prompter {
    private Library library;
    public boolean quitMenu = false;

    public Prompter(Library library){
        this.library = library;
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore ! \n\n");
    }

    public void displayMenu(){
        System.out.println("Menu options: ");
        System.out.println("1. List of books");
        System.out.println("2. Check out book");
        System.out.println("3. Check in book");
        System.out.println("4. Quit\n");

        boolean acceptableInput = false;

        while(!acceptableInput) {
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.print("Choose an option: ");
                int input = Integer.parseInt(scanner.nextLine());

                switch(input) {
                    case 1:
                        acceptableInput = true;
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
                        System.exit(0);
                    default:
                        System.out.println("Please select a valid option");
                }
            } catch (IllegalArgumentException iae) {
                System.out.println("Please select a valid option");
            }
        }
    }

    public void displayBooks(){
        System.out.printf("%40s\n\n", "+++++ available books ++++++");
        System.out.printf("%-22s%-22s%-22s%-22s\n","Author","Title","Published", "#");
        ArrayList<Book> booksArray = library.getBooks("available");
        int index = 1;
        for(Book book : booksArray){
            System.out.printf("%-22s%-22s%-22s%-22s\n", book.getAuthor(), book.getTitle(), book.getDate(), index);
            index++;
        }
        System.out.println("\n");
    }

    public void checkOutBook(){
        boolean acceptableInput = false;
        boolean checkOutResult = false;
        while(!acceptableInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter book number to check out: ");
                int input = Integer.parseInt(scanner.nextLine());
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
                System.out.print("Enter book number to check in: ");
                int input = Integer.parseInt(scanner.nextLine());
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
                System.out.println("\n Successfully checked in. Thank you !\n ");
                displayMenu();
            } else {
                displayMenu();
            }
        }
    }
}
