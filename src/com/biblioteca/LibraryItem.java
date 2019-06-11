package com.biblioteca;

public class LibraryItem {
    private boolean checkedOut = false;
    private String checkedOutUser = "";

    public LibraryItem() {
    }

    public boolean getCheckedStatus() {
        return this.checkedOut;
    }

    public void setCheckedOutUser(String user) {
        checkedOutUser = user;
    }

    public String getCheckedOutUser(){
        return this.checkedOutUser;
    }

    public boolean checkOut(){
        if(!checkedOut) {
            return checkedOut = true;
        }
        throw new IllegalArgumentException("\nSorry, that item is not available");
    }

    public boolean checkIn(){
        if(checkedOut){
            checkedOut = false;
            return true;
        }
        throw new IllegalArgumentException("\nSorry, that item is already checked in");
    }

}
