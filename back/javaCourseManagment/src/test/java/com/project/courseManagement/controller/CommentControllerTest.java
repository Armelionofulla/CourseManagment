package com.project.courseManagement.controller;

import com.project.courseManagement.dto.CommentWithStudentDTO;
import com.project.courseManagement.entity.Comment;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.service.CommentService;
import com.project.courseManagement.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentControllerTest {

    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentService commentService;

    @Mock
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        Long courseId = 1L;
        Comment comment = new Comment();
        Student student = new Student();
        when(studentService.findByUsernameAndPassword("username", "password")).thenReturn(student);

        commentController.create(courseId, "username", "password", comment);

        verify(studentService, times(1)).findByUsernameAndPassword("username", "password");
        verify(commentService, times(1)).create(student, courseId, comment);
    }

    @Test
    void getByCourseId() {

        Long courseId = 1L;
        List<CommentWithStudentDTO> comments = new ArrayList<>();
        Student student = new Student();
        when(studentService.findByUsernameAndPassword("username", "password")).thenReturn(student);
        when(commentService.getByCourseId(courseId)).thenReturn(comments);

        List<CommentWithStudentDTO> result = commentController.getByCourseId(courseId, "username", "password");


        assertEquals(comments, result);
        verify(studentService, times(1)).findByUsernameAndPassword("username", "password");
        verify(commentService, times(1)).getByCourseId(courseId);
    }

    @Test
    void likeComment() {

        Long commentId = 1L;
        Student student = new Student();
        when(studentService.findByUsernameAndPassword("username", "password")).thenReturn(student);

        commentController.likeComment(commentId, "username", "password");

        verify(studentService, times(1)).findByUsernameAndPassword("username", "password");
        verify(commentService, times(1)).likeComment(student, commentId);
    }
}
