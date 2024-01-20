package com.project.courseManagement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginObjectTest {

    @Test
    void getUsername() {
        LoginObject loginObject = new LoginObject("testUser", "testPassword");
        assertEquals("testUser", loginObject.getUsername());
    }

    @Test
    void getPassword() {
        LoginObject loginObject = new LoginObject("testUser", "testPassword");
        assertEquals("testPassword", loginObject.getPassword());
    }

    @Test
    void setUsername() {
        LoginObject loginObject = new LoginObject("testUser", "testPassword");
        loginObject.setUsername("newUser");
        assertEquals("newUser", loginObject.getUsername());
    }

    @Test
    void setPassword() {
        LoginObject loginObject = new LoginObject("testUser", "testPassword");
        loginObject.setPassword("newPassword");
        assertEquals("newPassword", loginObject.getPassword());
    }

}