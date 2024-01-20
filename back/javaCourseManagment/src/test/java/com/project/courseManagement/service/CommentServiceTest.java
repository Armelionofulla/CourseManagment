package com.project.courseManagement.service;

import com.project.courseManagement.dto.CommentWithStudentDTO;
import com.project.courseManagement.entity.Comment;
import com.project.courseManagement.entity.Course;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.repository.CommentRepository;
import com.project.courseManagement.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        // Arrange
        Student student = new Student();
        Long courseId = 1L;
        Comment comment = new Comment();
        Optional<Course> course = Optional.of(new Course());

        when(courseRepository.findById(courseId)).thenReturn(course);

        // Act
        commentService.create(student, courseId, comment);

        // Assert
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void getByCourseId() {
        // Arrange
        Long courseId = 1L;
        List<CommentWithStudentDTO> expectedComments = new ArrayList<>();

        when(commentRepository.getByCourseId(courseId)).thenReturn(expectedComments);

        // Act
        List<CommentWithStudentDTO> actualComments = commentService.getByCourseId(courseId);

        // Assert
        assertEquals(expectedComments, actualComments);
    }

}
