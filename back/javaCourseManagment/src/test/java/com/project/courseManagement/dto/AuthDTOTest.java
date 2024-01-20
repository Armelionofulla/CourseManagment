package com.project.courseManagement.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthDTOTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String expectedUsername = "testUser";
        String expectedPassword = "testPassword";

        // Act
        AuthDTO authDTO = new AuthDTO();
        authDTO.username = expectedUsername;
        authDTO.password = expectedPassword;

        // Assert
        assertEquals(expectedUsername, authDTO.username);
        assertEquals(expectedPassword, authDTO.password);
    }
}
