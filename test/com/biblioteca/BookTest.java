package com.biblioteca;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BookTest {

    private Book testBook;

    @BeforeEach
    public void setUp(){
        testBook = new Book("testAuthor", "testTitle", "testDate");
    }

    @Test
    public void bookMethodsReturnCorrectProperties() {
        assertEquals("testAuthor", testBook.getAuthor());
        assertEquals("testTitle", testBook.getTitle());
        assertEquals("testDate", testBook.getDate());
    }

    @Test
    public void booksCanBeCheckedOut () {
        testBook.checkOut();
        assertTrue(testBook.getCheckedStatus());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenBookAlreadyCheckedOut() {
        testBook.checkOut();
        Throwable checkedOutIae = assertThrows(IllegalArgumentException.class, () ->
            testBook.checkOut());
        assertEquals("\nSorry, that book is not available", checkedOutIae.getMessage());
    }


    @Test
    public void booksCanBeCheckedIn () {
        testBook.checkOut();
        testBook.checkIn();
        assertFalse(testBook.getCheckedStatus());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenBookAlreadyCheckedIn() {
        Throwable checkedInIae = assertThrows(IllegalArgumentException.class, () ->
                testBook.checkIn());
        assertEquals("\nSorry, that book is already checked in", checkedInIae.getMessage());
    }

}

