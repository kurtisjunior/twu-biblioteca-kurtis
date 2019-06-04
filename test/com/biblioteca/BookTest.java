package com.biblioteca;

import org.junit.Test;
import org.junit.Assert;

public class BookTest {

    @Test
    public void bookMethodsReturnCorrectProperties(){
        Book testBook = new Book("testAuthor", "testTitle", "testDate");

        Assert.assertEquals("testAuthor", testBook.getAuthor());
        Assert.assertEquals("testTitle", testBook.getTitle());
        Assert.assertEquals("testDate", testBook.getDate());
        Assert.assertEquals(1, testBook.getIndex());
    }

    @Test
    public void booksCanBeCheckedOut () {
        Book testBook = new Book("testAuthor", "testTitle", "testDate");

        testBook.setCheckedStatus("check out");
        Assert.assertTrue(testBook.getCheckedStatus());
    }

}
