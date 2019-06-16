package com.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    public Movie testMovie;

    @BeforeEach
    void setUp() {
        testMovie = new Movie("testName", "testYear", "testDirector", "testRating");
    }

    @Test
    public void returnsCorrectProperties(){
        assertEquals("testName", testMovie.getName());
        assertEquals("testYear", testMovie.getYear());
        assertEquals("testDirector", testMovie.getDirector());
        assertEquals("testRating", testMovie.getRating());
    }

    @Test
    public void moviesCanBeCheckedOut(){
        testMovie.checkOut();
        assertTrue(testMovie.getCheckedStatus());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenBookAlreadyCheckedOut() {
        testMovie.checkOut();
        Throwable checkedOutIae = assertThrows(IllegalArgumentException.class, () ->
                testMovie.checkOut());
        assertEquals("\nSorry, that item is not available", checkedOutIae.getMessage());
    }

    @Test
    public void moviesCanBeCheckedIn(){
        testMovie.checkOut();
        testMovie.checkIn();
        assertFalse(testMovie.getCheckedStatus());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenBookAlreadyCheckedIn() {
        Throwable checkedInIae = assertThrows(IllegalArgumentException.class, () ->
                testMovie.checkIn());
        assertEquals("\nSorry, that item is already checked in", checkedInIae.getMessage());
    }

    @Test
    public void movieDisplaysItselfNicely() {
        String display = String.format("%-28s%-28s%-28s%-28s%-28s%-28s%-28s", testMovie.getName(),("|"),testMovie.getYear(), ("|"), testMovie.getDirector(), ("|"), testMovie.getRating());
        assertEquals(display, testMovie.display());
    }



}