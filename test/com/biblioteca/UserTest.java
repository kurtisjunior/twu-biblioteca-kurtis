package com.biblioteca;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User testUser;

    @BeforeEach
    public void setUp(){
        testUser = new User("000-0000", "test", "testName", "testEmail", "testNumber");
    }

    @Test
    public void returnsProperties(){
        assertEquals("000-0000", testUser.getLibraryNumber());
        assertEquals("test", testUser.getPassword());
        assertEquals("testName", testUser.getName());
        assertEquals("testNumber", testUser.getNumber());
    }

    @Test
    public void returnsLoggedInStatus(){
        assertFalse(testUser.loggedInStatus());
    }

}