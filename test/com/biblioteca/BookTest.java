package com.biblioteca;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BookTest {

    private Book testBook;

    @BeforeEach
    public void setUp() {
        testBook = new Book("testAuthor", "testTitle", "testDate");
    }

    @Test
    public void bookMethodsReturnCorrectProperties() {
        assertEquals("testAuthor", testBook.getAuthor());
        assertEquals("testTitle", testBook.getTitle());
        assertEquals("testDate", testBook.getDate());
        assertEquals("", testBook.getCheckedOutUser());
    }

    @Test
    public void booksCanBeCheckedOut() {
        testBook.checkOut();
        assertTrue(testBook.getCheckedStatus());
    }
    @Test
    public void throwsIllegalArgumentExceptionWhenBookAlreadyCheckedOut() {
        testBook.checkOut();
        Throwable checkedOutIae = assertThrows(IllegalArgumentException.class, () ->
            testBook.checkOut());
        assertEquals("\nSorry, that item is not available", checkedOutIae.getMessage());
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
        assertEquals("\nSorry, that item is already checked in", checkedInIae.getMessage());
    }

    @Test
    public void recordsUserWhoHasCheckedOutTheBook(){
        testBook.setCheckedOutUser("000-0000");
    }

    @Test
    public void bookDisplaysItselfNicelyWhenNotCheckedOut() {
        String display = String.format("%-28s%-28s%-28s%-28s%-28s", testBook.getAuthor(),("|"),testBook.getTitle(), ("|"), testBook.getDate());
        assertEquals(display, testBook.display());
    }

    @Test
    public void bookDisplaysItselfNicelyWhenCheckedOut() {
        testBook.checkOut();
        String display = String.format("%-28s%-28s%-28s%-28s%-28s%-28s%-28s", testBook.getAuthor(),("|"),testBook.getTitle(), ("|"), testBook.getDate(), ("|"), testBook.getCheckedOutUser());
        assertEquals(display, testBook.display());
    }

}

