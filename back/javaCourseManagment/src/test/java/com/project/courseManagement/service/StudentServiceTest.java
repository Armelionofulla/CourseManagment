package com.project.courseManagement.service;

import com.project.courseManagement.entity.Student;
import com.project.courseManagement.repository.StudentRepository;
import com.project.courseManagement.security.PasswordEnc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        // Arrange
        Student student = new Student();

        // Act
        studentService.save(student);

        // Assert
        verify(studentRepository, times(1)).save(student);
    }


    @Test
    void findById() {
        // Arrange
        Long studentId = 1L;
        Student expectedStudent = new Student();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(expectedStudent));

        // Act
        Student actualStudent = studentService.findById(studentId);

        // Assert
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void usernameExists() {
        // Arrange
        String username = "john_doe";
        when(studentRepository.existsByUsername(username)).thenReturn(true);

        // Act
        boolean exists = studentService.usernameExists(username);

        // Assert
        assertTrue(exists);
    }

    @Test
    void emailExists() {
        // Arrange
        String email = "john.doe@example.com";
        when(studentRepository.existsByEmail(email)).thenReturn(true);

        // Act
        boolean exists = studentService.emailExists(email);

        // Assert
        assertTrue(exists);
    }

    @Test
    void findAll() {
        // Arrange
        List<Student> expectedStudents = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(expectedStudents);

        // Act
        List<Student> actualStudents = studentService.findAll();

        // Assert
        assertEquals(expectedStudents, actualStudents);
    }

    @Test
    void findByUsername() {
        // Arrange
        String username = "john_doe";
        Student expectedStudent = new Student();
        when(studentRepository.findByUsername(username)).thenReturn(expectedStudent);

        // Act
        Student actualStudent = studentService.findByUsername(username);

        // Assert
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void findByUsernameAndPassword() {
        // Arrange
        String username = "john_doe";
        String password = "password";
        Student expectedStudent = new Student();
        expectedStudent.setPassword(PasswordEnc.getSha256(password));
        when(studentRepository.findByUsername(username)).thenReturn(expectedStudent);

        // Act
        Student actualStudent = studentService.findByUsernameAndPassword(username, password);

        // Assert
        assertEquals(expectedStudent, actualStudent);
    }

}
