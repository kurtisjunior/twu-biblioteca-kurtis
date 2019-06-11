package com.biblioteca;

public class Movie extends LibraryItem {
    private String name;
    private String year;
    private String director;
    private String rating;

    public Movie(String name, String year, String director, String rating){
        super();
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getName(){
        return this.name;
    }

    public String getYear(){
        return this.year;
    }

    public String getDirector(){
        return this.director;
    }

    public String getRating() {
        return this.rating;
    }

}
