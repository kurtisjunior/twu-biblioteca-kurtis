package com.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    public Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("testName", "testYear", "testDirector", "testRating");
    }

    @Test
    public void returnsCorrectProperties(){
        assertEquals("testName", movie.getName());
        assertEquals("testYear", movie.getYear());
        assertEquals("testDirector", movie.getDirector());
        assertEquals("testRating", movie.getRating());
    }

    @Test
    public void moviesCanBeCheckedOut(){
        movie.checkOut();
        assertTrue(movie.getCheckedStatus());
    }
}