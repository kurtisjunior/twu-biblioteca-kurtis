package com.biblioteca;

public class User {

    private String libraryNumber;
    private String password;

    public User (String libraryNumber, String password){
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public String getLibraryNumber(){
        return this.libraryNumber;
    }

    public String getPassword(){
        return this.password;
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