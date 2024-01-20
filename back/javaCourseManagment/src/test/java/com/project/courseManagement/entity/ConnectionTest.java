package com.project.courseManagement.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectionTest {

    @Test
    void getId() {
        // Arrange
        Connection connection = new Connection();
        connection.setId(123L);

        // Act
        Long id = connection.getId();

        // Assert
        assertThat(id).isEqualTo(123L);
    }

    @Test
    void setId() {
        // Arrange
        Connection connection = new Connection();

        // Act
        connection.setId(456L);

        // Assert
        assertThat(connection.getId()).isEqualTo(456L);
    }

    @Test
    void canEqual() {
        // This method typically checks whether two objects of the class can be considered equal.
        // Depending on your implementation, you might need to provide specific test cases.
        Connection connection1 = new Connection();
        Connection connection2 = new Connection();

        assertThat(connection1.canEqual(connection2)).isTrue();
    }

    @Test
    void getStatus() {
        // Arrange
        Connection connection = new Connection();
        connection.setStatus("ACTIVE");

        // Act
        String status = connection.getStatus();

        // Assert
        assertThat(status).isEqualTo("ACTIVE");
    }

    @Test
    void getReceiver() {
        // Arrange
        Connection connection = new Connection();
        Student receiver = new Student();
        connection.setReceiver(receiver);

        // Act
        Student retrievedReceiver = connection.getReceiver();

        // Assert
        assertThat(retrievedReceiver).isEqualTo(receiver);
    }

    @Test
    void getSender() {
        // Arrange
        Connection connection = new Connection();
        Student sender = new Student();
        connection.setSender(sender);

        // Act
        Student retrievedSender = connection.getSender();

        // Assert
        assertThat(retrievedSender).isEqualTo(sender);
    }

    @Test
    void setStatus() {
        // Arrange
        Connection connection = new Connection();

        // Act
        connection.setStatus("INACTIVE");

        // Assert
        assertThat(connection.getStatus()).isEqualTo("INACTIVE");
    }

    @Test
    void setReceiver() {
        // Arrange
        Connection connection = new Connection();
        Student receiver = new Student();

        // Act
        connection.setReceiver(receiver);

        // Assert
        assertThat(connection.getReceiver()).isEqualTo(receiver);
    }

    @Test
    void setSender() {
        // Arrange
        Connection connection = new Connection();
        Student sender = new Student();

        // Act
        connection.setSender(sender);

        // Assert
        assertThat(connection.getSender()).isEqualTo(sender);
    }
}
