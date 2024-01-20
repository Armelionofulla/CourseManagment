package com.project.courseManagement.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {

    @Test
    void getId() {
        // Arrange
        Comment comment = new Comment();
        comment.setId(123L);

        // Act
        Long id = comment.getId();

        // Assert
        assertThat(id).isEqualTo(123L);
    }

    @Test
    void setId() {
        // Arrange
        Comment comment = new Comment();

        // Act
        comment.setId(456L);

        // Assert
        assertThat(comment.getId()).isEqualTo(456L);
    }

    @Test
    void canEqual() {
        // This method typically checks whether two objects of the class can be considered equal.
        // Depending on your implementation, you might need to provide specific test cases.
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();

        assertThat(comment1.canEqual(comment2)).isTrue();
    }

    @Test
    void getStudent() {
        // Arrange
        Comment comment = new Comment();
        Student student = new Student();
        comment.setStudent(student);

        // Act
        Student retrievedStudent = comment.getStudent();

        // Assert
        assertThat(retrievedStudent).isEqualTo(student);
    }

    @Test
    void getCourse() {
        // Arrange
        Comment comment = new Comment();
        Course course = new Course();
        comment.setCourse(course);

        // Act
        Course retrievedCourse = comment.getCourse();

        // Assert
        assertThat(retrievedCourse).isEqualTo(course);
    }

    @Test
    void getLikes() {
        // Arrange
        Comment comment = new Comment();
        comment.setLikes("42");

        // Act
        String likes = comment.getLikes();

        // Assert
        assertThat(likes).isEqualTo("42");
    }

    @Test
    void getText() {
        // Arrange
        Comment comment = new Comment();
        comment.setText("This is a test comment.");

        // Act
        String text = comment.getText();

        // Assert
        assertThat(text).isEqualTo("This is a test comment.");
    }

    @Test
    void setStudent() {
        // Arrange
        Comment comment = new Comment();
        Student student = new Student();

        // Act
        comment.setStudent(student);

        // Assert
        assertThat(comment.getStudent()).isEqualTo(student);
    }

    @Test
    void setCourse() {
        // Arrange
        Comment comment = new Comment();
        Course course = new Course();

        // Act
        comment.setCourse(course);

        // Assert
        assertThat(comment.getCourse()).isEqualTo(course);
    }

    @Test
    void setLikes() {
        // Arrange
        Comment comment = new Comment();

        // Act
        comment.setLikes("55");

        // Assert
        assertThat(comment.getLikes()).isEqualTo("55");
    }

    @Test
    void setText() {
        // Arrange
        Comment comment = new Comment();

        // Act
        comment.setText("Updated comment text.");

        // Assert
        assertThat(comment.getText()).isEqualTo("Updated comment text.");
    }
}
