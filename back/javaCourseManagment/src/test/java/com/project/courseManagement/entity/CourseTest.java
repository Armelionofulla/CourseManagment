package com.project.courseManagement.entity;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


class CourseTest {

    @Test
    void getId() {
        // Arrange
        Course course = new Course();
        course.setId(123L);

        // Act
        Long id = course.getId();

        // Assert
        assertThat(id).isEqualTo(123L);
    }

    @Test
    void setId() {
        // Arrange
        Course course = new Course();

        // Act
        course.setId(456L);

        // Assert
        assertThat(course.getId()).isEqualTo(456L);
    }

    @Test
    void canEqual() {
        // This method typically checks whether two objects of the class can be considered equal.
        // Depending on your implementation, you might need to provide specific test cases.
        Course course1 = new Course();
        Course course2 = new Course();

        assertThat(course1.canEqual(course2)).isTrue();
    }

    @Test
    void getName() {
        // Arrange
        Course course = new Course();
        course.setName("Introduction to Programming");

        // Act
        String name = course.getName();

        // Assert
        assertThat(name).isEqualTo("Introduction to Programming");
    }

    @Test
    void setName() {
        // Arrange
        Course course = new Course();

        // Act
        course.setName("Data Structures and Algorithms");

        // Assert
        assertThat(course.getName()).isEqualTo("Data Structures and Algorithms");
    }

    @Test
    void getStartDate() {
        // Arrange
        Course course = new Course();
        Date startDate = new Date();
        course.setStartDate(startDate);

        // Act
        Date retrievedStartDate = course.getStartDate();

        // Assert
        assertThat(retrievedStartDate).isEqualTo(startDate);
    }

    @Test
    void setStartDate() {
        // Arrange
        Course course = new Course();
        Date startDate = new Date();

        // Act
        course.setStartDate(startDate);

        // Assert
        assertThat(course.getStartDate()).isEqualTo(startDate);
    }

    @Test
    void getEndDate() {
        // Arrange
        Course course = new Course();
        Date endDate = new Date();
        course.setEndDate(endDate);

        // Act
        Date retrievedEndDate = course.getEndDate();

        // Assert
        assertThat(retrievedEndDate).isEqualTo(endDate);
    }

    @Test
    void setEndDate() {
        // Arrange
        Course course = new Course();
        Date endDate = new Date();

        // Act
        course.setEndDate(endDate);

        // Assert
        assertThat(course.getEndDate()).isEqualTo(endDate);
    }

    @Test
    void getType() {
        // Arrange
        Course course = new Course();
        course.setType("Online");

        // Act
        String type = course.getType();

        // Assert
        assertThat(type).isEqualTo("Online");
    }

    @Test
    void setType() {
        // Arrange
        Course course = new Course();

        // Act
        course.setType("In-person");

        // Assert
        assertThat(course.getType()).isEqualTo("In-person");
    }

    @Test
    void getImage() {
        // Arrange
        Course course = new Course();
        course.setImage("course_image.jpg");

        // Act
        String image = course.getImage();

        // Assert
        assertThat(image).isEqualTo("course_image.jpg");
    }

    @Test
    void setImage() {
        // Arrange
        Course course = new Course();

        // Act
        course.setImage("new_course_image.jpg");

        // Assert
        assertThat(course.getImage()).isEqualTo("new_course_image.jpg");
    }

    @Test
    void getCourse_time() {
        // Arrange
        Course course = new Course();
        Time courseTime = Time.valueOf("10:00:00");
        course.setCourse_time(courseTime);

        // Act
        Time retrievedCourseTime = course.getCourse_time();

        // Assert
        assertThat(retrievedCourseTime).isEqualTo(courseTime);
    }

    @Test
    void setCourse_time() {
        // Arrange
        Course course = new Course();
        Time courseTime = Time.valueOf("14:30:00");

        // Act
        course.setCourse_time(courseTime);

        // Assert
        assertThat(course.getCourse_time()).isEqualTo(courseTime);
    }

    @Test
    void getLecture_hall() {
        // Arrange
        Course course = new Course();
        course.setLecture_hall("Room 101");

        // Act
        String lectureHall = course.getLecture_hall();

        // Assert
        assertThat(lectureHall).isEqualTo("Room 101");
    }

    @Test
    void setLecture_hall() {
        // Arrange
        Course course = new Course();

        // Act
        course.setLecture_hall("Room 201");

        // Assert
        assertThat(course.getLecture_hall()).isEqualTo("Room 201");
    }

}
