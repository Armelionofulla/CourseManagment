package com.project.courseManagement.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncTest {


    @Test
    void getSha256_emptyString() {
        // Test with an empty string
        String hashedPassword = PasswordEnc.getSha256("");
        assertNotNull(hashedPassword);
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", hashedPassword);
    }

    @Test
    void getSha256_nullString() {
        // Test with a null string
        assertThrows(RuntimeException.class, () -> PasswordEnc.getSha256(null));
    }

    // Add more test cases as needed to cover different scenarios

}
