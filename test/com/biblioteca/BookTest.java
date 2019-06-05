package com.biblioteca;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class BookTest {

    private static Book testBook;

    @BeforeAll
    public static void setUp(){
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
        assertThrows(IllegalArgumentException.class, () ->
                testBook.setCheckedOut());
    }

    @Test
    public void booksCanBeCheckedIn () {
        testBook.setCheckedOut();
        testBook.setCheckedIn();
        assertFalse(testBook.getCheckedStatus());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenBookAlreadyCheckedIn() {
        testBook.setCheckedIn();
        assertThrows(IllegalArgumentException.class, () ->
                testBook.setCheckedIn());
    }

}

