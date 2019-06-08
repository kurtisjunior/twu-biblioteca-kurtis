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
        String [] menuOptions = {"Menu Options: ", "1. List of books", "2. List of movies", "3. Check out book", "4. Check out movie","5. Return book", "6. Return movie", "7. Checked out books", "8. Quit"};
        for(String option : menuOptions) {
            System.out.println(option);
        }
        System.out.print("\n");
        menuInput();
    }

    public void handleMenu(int input) {
        switch (input) {
            case 1:
                displayAvailableBooks();
                break;
            case 2:
                displayMovies();
                break;
            case 3:
                validateUserCredentials("book", "check out");
                break;
            case 4:
                validateUserCredentials("movie", "check out");
                break;
            case 5:
                validateUserCredentials("book", "check in");
                break;
            case 6:
                validateUserCredentials("movie", "check in");
                break;
            case 7:
                displayCheckedBooks();
                break;
            case 8:
                quitMenu = true;
                System.out.println("\nThanks for using Biblioteca !");
                System.exit(0);
            default:
                System.out.println("Please select a valid option");
        }
}

    public void displayAvailableBooks(){
        System.out.printf("%80s\n\n", "-------- Available books --------");
        System.out.printf("%-28s%-28s%-28s%-28s%-28s\n","*** Author ***", (""), "*** Title ***", (""), "*** Published ***");
        ArrayList<Book> booksArray = library.getBooks("available");
        for(Book book : booksArray){
            System.out.printf("%-28s%-28s%-28s%-28s%-28s\n", book.getAuthor(),("|"),book.getTitle(), ("|"), book.getDate());
        }
        System.out.println("\n");
    }

    public void displayCheckedBooks(){
        System.out.printf("%100s\n\n", "-------- Checked out books --------");
        System.out.printf("%-28s%-28s%-28s%-28s%-28s%-28s%-28s\n","*** Author ***", (""), "*** Title ***", (""), "*** Published ***", (""), "*** User ***");
        ArrayList<Book> booksArray = library.getBooks("checked");
        for(Book book : booksArray){
            System.out.printf("%-28s%-28s%-28s%-28s%-28s%-28s%-28s\n", book.getAuthor(),("|"),book.getTitle(), ("|"), book.getDate(), ("|"), book.getCheckedOutUser());
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


    public String getUserInput(String inputType) {
        Scanner scanner = new Scanner(System.in);
        System.out.print( inputType == "libraryNumber" ? "Enter library number: " : inputType == "password" ? "Enter password: " : "Enter title: ");
        String input = (scanner.nextLine());
        return input;
    }

    public void validateUserCredentials(String type, String action){
        Boolean success = false;
        String libraryNumber = getUserInput("libraryNumber");
        String password = getUserInput("password");
        try{
            success = library.userLogIn(libraryNumber, password);

        }catch(IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        //switch statement refactor
        if(success && type == "book" && action == "check out"){
            checkOutItem(type, libraryNumber);
        } else if (success && type == "movie" && action == "check out"){
            checkOutItem(type, libraryNumber);
        } else if (success && type == "book" && action == "check in"){
            checkInItem(type, libraryNumber);
        } else if (success && type == "movie" && action == "check in"){
            checkInItem(type, libraryNumber);
        }
    }

    public void checkOutItem(String type, String libraryNumber){
        String input = getUserInput("title");
        if(type.equals("book")) checkOut(input, "book", libraryNumber); else checkOut(input, "movie", libraryNumber);
    }

    public void checkInItem(String type, String libraryNumber){
        String input = getUserInput("title");
        if(type.equals("book")) checkIn(input, "book", libraryNumber); else checkIn(input, "movie", libraryNumber);
    }

    public void checkOut(String input, String type, String libraryNumber){
        boolean success = false;
        try{
            success = (type.equals("book")) ? library.bookAction(input, "check out", libraryNumber): library.movieAction(input, "check out");
        } catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        System.out.println(((success) && type == "book") ? "\nSuccessfully checked out. Thank you ! Enjoy the book\n" : (success) ? "\nSuccessfully checked out. Thank you ! Enjoy the movie\n" : "\n");
    }

    public void checkIn(String input, String type, String libraryNumber){
        boolean success = false;
        try{
            success = (type.equals("book")) ? library.bookAction(input, "check in", libraryNumber) : library.movieAction(input, "check in");
        } catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        System.out.println((((success)) ? "\nSuccessfully returned. Thank you !\n" : "\n"));
    }
}
