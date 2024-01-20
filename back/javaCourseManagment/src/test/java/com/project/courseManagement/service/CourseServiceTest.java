package com.project.courseManagement.service;

import com.project.courseManagement.dto.CourseStudentDTO;
import com.project.courseManagement.entity.Course;
import com.project.courseManagement.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        // Arrange
        HashMap<String, List<Course>> expectedCourses = new HashMap<>();
        List<Course> majorCourses = new ArrayList<>();
        List<Course> minorCourses = new ArrayList<>();
        expectedCourses.put("major", majorCourses);
        expectedCourses.put("minor", minorCourses);
        when(courseRepository.findMajor()).thenReturn(majorCourses);
        when(courseRepository.findMinor()).thenReturn(minorCourses);

        // Act
        HashMap<String, List<Course>> actualCourses = courseService.findAll();

        // Assert
        assertEquals(expectedCourses, actualCourses);
    }

    @Test
    void getCourseById() {
        // Arrange
        Long courseId = 1L;
        List<CourseStudentDTO> expectedCourseDTOs = new ArrayList<>();
        when(courseRepository.getCourseById(courseId)).thenReturn(expectedCourseDTOs);

        // Act
        List<CourseStudentDTO> actualCourseDTOs = courseService.getCourseById(courseId);

        // Assert
        assertEquals(expectedCourseDTOs, actualCourseDTOs);
    }

    @Test
    void findById() {
        // Arrange
        Long courseId = 1L;
        Optional<Course> expectedCourse = Optional.of(new Course());
        when(courseRepository.findById(courseId)).thenReturn(expectedCourse);

        // Act
        Optional<Course> actualCourse = courseService.findById(courseId);

        // Assert
        assertEquals(expectedCourse, actualCourse);
    }

    @Test
    void findByStudent() {
        // Arrange
        Long studentId = 1L;
        List<Course> expectedCourses = new ArrayList<>();
        when(courseRepository.findByStudent_Id(studentId)).thenReturn(expectedCourses);

        // Act
        List<Course> actualCourses = courseService.findByStudent(studentId);

        // Assert
        assertEquals(expectedCourses, actualCourses);
    }
}