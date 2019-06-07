package com.biblioteca;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User testUser;

    @BeforeEach
    public void setUp(){
        testUser = new User("000-0000", "test");
    }

    @Test
    public void returnsUserLibraryNumber(){
        assertEquals("000-0000", testUser.getLibraryNumber());
    }

    @Test
    public void returnsUserPassword(){
        assertEquals("test", testUser.getPassword());
    }

}