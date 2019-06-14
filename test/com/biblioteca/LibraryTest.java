package com.biblioteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;



public class LibraryTest  {

    private ArrayList<Book> testBooksArray;
    private ArrayList<Movie> testMoviesArray;
    private ArrayList<User> testUserArray;
    private Library library;

    @BeforeEach
    public void setUpBooks() {
        testBooksArray = new ArrayList<>();
        testMoviesArray = new ArrayList<>();
        testUserArray = new ArrayList<>();

        testBooksArray.add(new Book("testAuthor", "testTitle", "testDate"));
        testBooksArray.add(new Book("testAuthorTwo", "testTitleTwo", "testDateTwo"));

        testMoviesArray.add(new Movie("testName", "testYear", "testDirector", "testRating"));
        testMoviesArray.add(new Movie("testNameTwo", "testYearTwo", "testDirectorTwo", "testRatingTwo"));

        testUserArray.add(new User("000-0000", "testPassword", "testName", "testEmail", "testNumber"));
        testUserArray.add(new User("000-0001", "testPasswordTwo", "testNameTwo", "testEmailTwo", "testNumberTwo"));
        library = new Library(testBooksArray, testMoviesArray, testUserArray);
    }

    @Test
    public void userCanCheckOutBook() {
        assertTrue(library.bookAction("testTitle", "check out", "0"));
    }

    @Test
    public void checkedOutBooksAreFilteredFromBookList() {
        library.bookAction("testTitle", "check out", "0");
        ArrayList<Book> availableBooks = library.getBooks("available");

        assertEquals(1, availableBooks.size());
        assertEquals("testAuthorTwo", availableBooks.get(0).getAuthor());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenCheckedOutBookDoesNotExist() {
        Throwable checkedOutIae = assertThrows(IllegalArgumentException.class, () ->
                library.bookAction("invalid title", "check out", "0"));
        assertEquals("The book you have selected does not exist, please try again\n\n", checkedOutIae.getMessage());
    }

    @Test
    public void userCanCheckInBook(){
        library.bookAction("testTitle", "check out", "000-0000");
        assertTrue(library.bookAction("testTitle", "check in", "000-0000"));
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenCheckedInBookDoesNotExist() {
        Throwable checkedInIae = assertThrows(IllegalArgumentException.class, () ->
                library.bookAction("invalid title", "check in", "0"));
        assertEquals("The book you have selected does not exist, please try again\n\n", checkedInIae.getMessage());
    }

    @Test
    public void titleCaseDefaultsToLowerCase(){
        assertEquals("testtitle", library.toLowerCase("TESTTITLE"));
    }

    @Test
    public void userCanCheckOutBookInAnyCase(){
        assertTrue(library.bookAction("TESTTITLE", "check out", "0"));
    }

    @Test
    public void userCanCheckInBookInAnyCase(){
        library.bookAction("testTitle", "check out", "000-0000");
        library.bookAction("TESTTITLE", "check in", "000-0000");
    }

    @Test
    public void userCanLogIn(){
        assertTrue(library.userLogIn("000-0000", "testPassword"));
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenUsernameOrPasswordIsIncorrect(){
        Throwable credentialIae = assertThrows(IllegalArgumentException.class, () ->
                library.userLogIn("000-0001", "testPassword"));
        assertEquals("\nlogin failed, incorrect library number or password\n", credentialIae.getMessage());
    }

    @Test
    public void returnsCheckedOutBooks(){
        library.bookAction("testTitle", "check out", "0");
        ArrayList<Book> availableBooks = library.getBooks("checked");

        assertEquals(1, availableBooks.size());
        assertEquals("testAuthor", availableBooks.get(0).getAuthor());
    }

    @Test
    public void throwsIllegalArgumentExceptionWhenUserChecksInABookByAnotherUser(){
        library.bookAction("testTitle", "check out", "000-0000");
        Throwable incorrectBookIae = assertThrows(IllegalArgumentException.class, () ->
                library.bookAction("testTitle", "check in", "000-0001")
        );
        assertEquals("The book you have selected does not exist, please try again\n\n", incorrectBookIae.getMessage());
    }


    @Test
    public void checkOutBookSuccessfully(){
        library.checkOutItem(LibraryItemType.BOOK,"testTitle", LibraryItemAction.CHECK_OUT, "000-0000");
        ArrayList<Book> availableBooks = library.getBooks("checked");

        assertEquals(1, availableBooks.size());
        assertEquals("testAuthor", availableBooks.get(0).getAuthor());

    }

    @Test
    public void checkOutMovieSuccessfully(){
        library.checkOutItem(LibraryItemType.MOVIE, "testName", LibraryItemAction.CHECK_OUT, "000-0000");

        assertTrue(testMoviesArray.get(0).getCheckedStatus());
        assertFalse(testMoviesArray.get(1).getCheckedStatus());
    }






}





