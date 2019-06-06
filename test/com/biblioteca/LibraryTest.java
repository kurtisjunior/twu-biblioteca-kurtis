package com.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;



public class LibraryTest {

    private ArrayList<Book> testBooksArray;
    private ArrayList<Movie> testMoviesArray;
    private Library library;

    @BeforeEach
    public void setUpBooks() {
        testBooksArray = new ArrayList<>();
        testMoviesArray = new ArrayList<>();
        testBooksArray.add(new Book("testAuthor", "testTitle", "testDate"));
        testBooksArray.add(new Book("testAuthorTwo", "testTitleTwo", "testDateTwo"));
        testMoviesArray.add(new Movie("testName", "testYear", "testDirector", "testRating"));
        testMoviesArray.add(new Movie("testNameTwo", "testYearTwo", "testDirectorTwo", "testRatingTwo"));
        library = new Library(testBooksArray, testMoviesArray);
    }

    @Test
    public void ReturnsAllBooks() {
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
        Throwable checkedOutIae = assertThrows(IllegalArgumentException.class, () ->
                library.checkOut("invalid title"));
        assertEquals("\nThe book you have selected does not exist, please try again\n\n", checkedOutIae.getMessage());
    }

    @Test
    public void userCanCheckInBook(){
        library.checkOut("testTitle");
        assertTrue(library.checkIn("testTitle"));
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenCheckedInBookDoesNotExist() {
        Throwable checkedInIae = assertThrows(IllegalArgumentException.class, () ->
                library.checkIn("invalid title"));
        assertEquals("The book you have selected does not exist, please try again\n\n", checkedInIae.getMessage());
    }

    @Test
    public void titleCaseDefaultsToLowerCase(){
        assertEquals("testtitle", library.toLowerCase("TESTTITLE"));
    }

    @Test
    public void userCanCheckOutBookInAnyCase(){
        assertTrue(library.checkOut("TESTTITLE"));
    }

    @Test
    public void userCanCheckInBookInAnyCase(){
        library.checkOut("testTitle");
        library.checkIn("TESTTITLE");
    }

    @Test
    public void returnsAllMovies(){
        assertEquals(testMoviesArray, library.getMovies());
    }
}




