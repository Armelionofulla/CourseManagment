package com.project.courseManagement.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BaseTest {

    @Test
    void getId() {
        // Arrange
        Base base = new Base();
        base.setId(123L);

        // Act
        Long id = base.getId();

        // Assert
        assertThat(id).isEqualTo(123L);
    }

    @Test
    void setId() {
        // Arrange
        Base base = new Base();

        // Act
        base.setId(456L);

        // Assert
        assertThat(base.getId()).isEqualTo(456L);
    }

}
