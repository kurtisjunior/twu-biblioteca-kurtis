package com.biblioteca;

public class User {

    private String libraryNumber;
    private String password;
    private boolean loggedIn;
    private String name;
    private String email;
    private String number;

    public User (String libraryNumber, String password, String name, String email, String number){
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.loggedIn = false;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getLibraryNumber(){
        return this.libraryNumber;
    }

    public String getPassword(){
        return this.password;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getNumber(){
        return this.number;
    }

    public Boolean loggedInStatus(){
        return this.loggedIn;
    }

}


/*
create and test user object
invoke a login credential function from menu
    get user input for username and password
    check username and password match
        if they do not then prompt until they do
invoke function for title name
 */