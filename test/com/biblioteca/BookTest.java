package com.biblioteca;

import org.junit.Test;
import org.junit.Assert;

public class BookTest {
    Book testBook = new Book("testAuthor", "testTitle", "testDate");

    @Test
    public void bookMethodsReturnCorrectProperties(){
        Assert.assertEquals("testAuthor", testBook.getAuthor());
        Assert.assertEquals("testTitle", testBook.getTitle());
        Assert.assertEquals("testDate", testBook.getDate());
    }

    @Test
    public void booksCanBeCheckedOut () {
        Assert.assertTrue(testBook.isCheckedOut());
    }

}
