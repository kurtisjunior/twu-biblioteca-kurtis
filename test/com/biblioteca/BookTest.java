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
        assertEquals(1, testBook.getIndex());
    }

    @Test
    public void booksCanBeCheckedOut () {
        testBook.setCheckedStatus("check out");
        assertTrue(testBook.getCheckedStatus());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenBookAlreadyCheckedOut() {
        assertThrows(IllegalArgumentException.class, () ->
                testBook.setCheckedStatus("check out"));
    }

}

