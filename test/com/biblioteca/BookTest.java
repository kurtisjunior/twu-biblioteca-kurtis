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
        testBook.setCheckedOut();
        assertTrue(testBook.getCheckedStatus());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenBookAlreadyCheckedOut() {
        testBook.setCheckedOut();
        Throwable checkedOutIae = assertThrows(IllegalArgumentException.class, () ->
            testBook.setCheckedOut());
        assertEquals("Sorry, that book is not available\n", checkedOutIae.getMessage());
    }


    @Test
    public void booksCanBeCheckedIn () {
        testBook.setCheckedOut();
        testBook.setCheckedIn();
        assertFalse(testBook.getCheckedStatus());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenBookAlreadyCheckedIn() {
        Throwable checkedInIae = assertThrows(IllegalArgumentException.class, () ->
                testBook.setCheckedIn());
        assertEquals("Sorry, that book is already checked in\n", checkedInIae.getMessage());
    }

}

