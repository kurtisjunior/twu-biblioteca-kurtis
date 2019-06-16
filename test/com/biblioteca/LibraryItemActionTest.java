package com.biblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryItemActionTest {

    @Test
    public void ReturnsCheckOutValue(){
        assertEquals("check out", LibraryItemAction.CHECK_OUT.getValue());
    }

    @Test
    public void ReturnsCheckInValue(){
        assertEquals("check in", LibraryItemAction.CHECK_IN.getValue());
    }
}