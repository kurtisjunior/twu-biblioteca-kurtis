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
        System.out.printf("\n%120s", "**** Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore ! **** \n\n");
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
        String [] menuOptions = {"Menu Options: ", "1. List of books", "2. List of movies", "3. Check out book", "4. Check out movie","5. Check in book", "6. Check in movie", "7. Return book", "8. Quit"};
        for(String option : menuOptions) {
            System.out.println(option);
        }
        System.out.println("\n");
        menuInput();
    }

    public void handleMenu(int input) {
        switch (input) {
            case 1:
                displayBooks();
                break;
            case 2:
                displayMovies();
                break;
            case 3:
                checkOutItem("book");
                break;
            case 4:
                checkOutItem("movie");
                break;
            case 5:
                checkInItem("book");
                break;
            case 6:
                checkInItem("movie");
                break;
            case 7:
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

    public void displayMovies(){
        System.out.printf("%100s\n\n", "-------- Available movies --------");
        System.out.printf("%-28s%-28s%-28s%-28s%-28s%-28s%-28s\n","*** Name ***", (""), "*** Year ***", (""), "*** Director ***", (""), "*** Rating ***");
        ArrayList<Movie> moviesArray = library.getMovies("available");
        for(Movie movie : moviesArray){
            System.out.printf("%-28s%-28s%-28s%-28s%-28s%-28s%-28s\n", movie.getName(),("|"),movie.getYear(), ("|"), movie.getDirector(), ("|"), movie.getRating());
        }
        System.out.println("\n");
    }


    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title: ");
        String input = (scanner.nextLine());
        return input;
    }

    public void checkOutItem(String type){
        String input = getUserInput();
        if(type.equals("book")) checkOut(input, "book"); else checkOut(input, "movie");
    }

    public void checkInItem(String type){
        String input = getUserInput();
        if(type.equals("book")) checkIn(input, "book"); else checkIn(input, "movie");
    }

    public void checkOut(String input, String type){
        boolean success = false;
        try{
            success = (type.equals("book")) ? library.bookAction(input, "check out") : library.movieAction(input, "check out");
        } catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        System.out.println(((success) && type == "book") ? "\nSuccessfully checked out. Thank you ! Enjoy the book\n" : (success) ? "Successfully checked out. Thank you ! Enjoy the movie" : "\n");
    }

    public void checkIn(String input, String type){
        boolean success = false;
        try{
            success = (type.equals("book")) ? library.bookAction(input, "check in") : library.movieAction(input, "check in");
        } catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        System.out.println((((success)) ? "\nSuccessfully returned. Thank you !\n" : "\n"));
    }
}
