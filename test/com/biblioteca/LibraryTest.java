package com.biblioteca;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;



public class LibraryTest {

    private static ArrayList<Book> testBooksArray;
    private static Library library;

    @BeforeAll
    public static void setUp() {
        testBooksArray = new ArrayList<>();
        testBooksArray.add(new Book("testAuthor", "testTitle", "testDate"));
        testBooksArray.add(new Book("testAuthorTwo", "testTitleTwo", "testDateTwo"));
        library = new Library(testBooksArray);
    }

    @Test
    public void LibraryReturnsAnArrayOfAllBooks() {
        assertEquals(testBooksArray, library.getBooks());
    }

    @Test
    public void userCanCheckOutBook() {
        assertTrue(library.checkOut("testTitle"));
    }

    @Test
    public void checkedOutBooksAreFilteredFromBookList() {
        library.checkOut("testTitle");
        ArrayList<Book> availableBooks = library.getBooks("available");

        assertEquals(1, availableBooks.size());
        assertEquals("testAuthorTwo", availableBooks.get(0).getAuthor());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenCheckedOutBookDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () ->
            library.checkOut("testTitleThree"));
    }

    @Test
    public void userCanCheckInBook(){
        library.checkOut("testTitle");
        assertTrue(library.checkIn("testTitle"));
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenCheckedInBookDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () ->
                library.checkIn("testTitleThree"));
    }

    @Test
    public void titleCaseDefaultsToLowerCase(){
        assertEquals("testtitle", library.toLowerCase("TESTTITLE"));
    }

    @Test
    public void userCanCheckOutBookInAnyCase(){
        library.checkOut("TESTTITLE");
    }

    @Test
    public void userCanCheckInBookInAnyCase(){
        library.checkOut("testTitle");
        library.checkIn("TESTTITLE");
    }
}




