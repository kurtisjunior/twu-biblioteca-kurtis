package com.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Prompter {
    private String AVAILABLE_BOOKS_HEADER = String.format("%80s\n\n", "-------- Available books --------");
    private String CHECKEDOUT_BOOKS_HEADER = String.format("%100s\n\n", "-------- Checked out books --------");
    private String AVAILABLE_BOOKS_COLUMNS = String.format("%-28s%-28s%-28s%-28s%-28s\n","*** Author ***", (""), "*** Title ***", (""), "*** Published ***");
    private String CHECKEDOUT_BOOKS_COLUMNS = String.format("%-28s%-28s%-28s%-28s%-28s%-28s%-28s\n","*** Author ***", (""), "*** Title ***", (""), "*** Published ***", (""), "*** User ***");
    private String AVAILABLE_MOVIE_HEADER = String.format("%100s\n\n", "-------- Available movies --------");
    private String AVAILABLE_MOVIE_COLUMNS = String.format("%-28s%-28s%-28s%-28s%-28s%-28s%-28s\n","*** Name ***", (""), "*** Year ***", (""), "*** Director ***", (""), "*** Rating ***");


    private Library library;
    private boolean quitMenu = false;
    private Boolean userLoggedIn = false;

    public Prompter(Library library) {
        this.library = library;
    }

    public void start(){
        displayWelcomeMessage();

        while(!quitMenu){
            if(!userLoggedIn) {
                displayPreLoginMenu();
            } else {
                displayLoggedInMenu();
            }
        }
        System.out.println("\nThanks for using Biblioteca !");
        System.exit(0);
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
                handleInput(input);
                acceptableInput = true;
            } catch (IllegalArgumentException iae) {
                System.out.println("Please enter a valid menu option");
            }
        }
    }

    public void displayPreLoginMenu() {
        String [] menuOptions = {"Menu Options: ", "1. List of books", "2. List of movies", "3. Checked out books", "4. Log in", "5. Quit"};
        for(String option : menuOptions) {
            System.out.println(option);
        }
        System.out.print("\n");
        menuInput();
    }

    public void displayLoggedInMenu() {
        String [] menuOptions = {"Menu Options: ", "1. List of books", "2. List of movies", "3. Check out book", "4. Check out movie", "5. Return book", "6. Return movie", "7. Log out", "8. Quit"};
        for(String option : menuOptions) {
            System.out.println(option);
        }
        System.out.print("\n");
        menuInput();
    }

    public void handleInput(int input){
        if(userLoggedIn){
            handleLoggedInMenu(input);
        } else {
            handlePreLoggedInMenu(input);
        }
    }

    public void handlePreLoggedInMenu(int input) {
        switch (input) {
            case 1:
                display(AVAILABLE_BOOKS_HEADER, AVAILABLE_BOOKS_COLUMNS, library.getBooks("available"));
                break;
            case 2:
                display(AVAILABLE_MOVIE_HEADER, AVAILABLE_MOVIE_COLUMNS, library.getMovies("available"));
                break;
            case 3:
                display(CHECKEDOUT_BOOKS_HEADER, CHECKEDOUT_BOOKS_COLUMNS, library.getBooks("checked"));
                break;
            case 4:
                validateUserCredentials();
                break;
            case 5:
                quitMenu = true;

            default:
                System.out.println("Please select a valid option");
        }
    }

    public void handleLoggedInMenu(int input) {
        switch (input) {
            case 1:
                display(AVAILABLE_BOOKS_HEADER, AVAILABLE_BOOKS_COLUMNS, library.getBooks("available"));
                break;
            case 2:
                display(AVAILABLE_MOVIE_HEADER, AVAILABLE_MOVIE_COLUMNS, library.getMovies("available"));
                break;
            case 3:
                performAction(LibraryItemType.BOOK, LibraryItemAction.CHECK_OUT);
                break;
            case 4:
                performAction(LibraryItemType.MOVIE, LibraryItemAction.CHECK_OUT);
                break;
            case 5:
                performAction(LibraryItemType.BOOK, LibraryItemAction.CHECK_IN);
                break;
            case 6:
                performAction(LibraryItemType.MOVIE, LibraryItemAction.CHECK_IN);
                break;
            case 7:
                userLoggedIn = false;
                library.clearLoggedInUser();
                break;
            case 8:
                quitMenu = true;
            default:
                System.out.println("Please select a valid option");
        }
    }

    void display(String header, String columns, ArrayList<? extends Displayable> items) {
        System.out.print(header);
        System.out.print(columns);

        for(Displayable item : items){
            System.out.println(item.display());
        }

        System.out.println("\n");
    }


    public String getUserInput(UserInputAction inputType) {
        Scanner scanner = new Scanner(System.in);
        System.out.print( inputType.equals("libraryNumber") ? "Enter library number: " : inputType.equals("password") ? "Enter password: " : "Enter title: ");
        String input = (scanner.nextLine());
        return input;
    }


    public void validateUserCredentials(){
        Boolean success = false;
        String libraryNumber = getUserInput(UserInputAction.LIBRARY_NUMBER);
        String password = getUserInput(UserInputAction.PASSWORD);
        try{
            success = library.userLogIn(libraryNumber, password);
        }catch(IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        if(success){
            userLoggedIn = true;
            ArrayList<User> loggedInUser = library.getLoggedInUser();
            System.out.printf("\nSuccessfully logged in, welcome back %s \n", loggedInUser.get(0).getName());
            System.out.printf("%s\n", loggedInUser.get(0).getEmail());
            System.out.printf("%s\n\n", loggedInUser.get(0).getNumber());
            displayLoggedInMenu();
        }
    }


    private String getLibraryNumber() {
        ArrayList<User> loggedInUser = library.getLoggedInUser();
        return loggedInUser.get(0).getLibraryNumber();
    }

    private void performAction(LibraryItemType type, LibraryItemAction action) {
        String input = getUserInput(UserInputAction.TITLE);
        String libraryNumber = getLibraryNumber();

        try {
            library.checkOutItem(type, input, action, libraryNumber);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

        System.out.printf("\nSuccessfully checked out. Thank you ! Enjoy the %s\n", type.getValue());
    }
}
