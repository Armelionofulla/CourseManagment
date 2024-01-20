package com.project.courseManagement.service;

import com.project.courseManagement.dto.ConnectionsDTO;
import com.project.courseManagement.entity.Connection;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.repository.ConnectionRepository;
import com.project.courseManagement.repository.CourseRepository;
import com.project.courseManagement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConnectionServiceTest {

    @InjectMocks
    private ConnectionService connectionService;

    @Mock
    private ConnectionRepository connectionRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addStatus() {
        // Arrange
        Connection connection = new Connection();
        when(connectionRepository.save(connection)).thenReturn(connection);

        // Act
        Connection savedConnection = connectionService.addStatus(connection);

        // Assert
        assertEquals(connection, savedConnection);
    }

    @Test
    void createConnectionRequest() {
        // Arrange
        Student sender = new Student();
        Long receiverId = 1L;
        Connection connection = new Connection();
        connection.setStatus("pending");
        connection.setSender(sender);
        Optional<Student> receiver = Optional.of(new Student());
        when(studentRepository.findById(receiverId)).thenReturn(receiver);
        when(connectionRepository.save(connection)).thenReturn(connection);

        // Act
        ResponseEntity<String> responseEntity = connectionService.createConnectionRequest(sender, receiverId);

        // Assert
        assertEquals(new ResponseEntity<>("Success", HttpStatus.CREATED), responseEntity);
    }

    @Test
    void acceptConection() {
        // Arrange
        Long receiverId = 1L;
        Long senderId = 2L;
        Connection connection = new Connection();
        Optional<Connection> optionalConnection = Optional.of(connection);
        when(connectionRepository.findById(receiverId)).thenReturn(optionalConnection);
        when(connectionRepository.save(connection)).thenReturn(connection);

        // Act
        ResponseEntity<String> responseEntity = connectionService.acceptConection(receiverId, senderId);

        // Assert
        assertEquals(new ResponseEntity<>("Success", HttpStatus.CREATED), responseEntity);
    }

    @Test
    void getConnections() {
        // Arrange
        Long studentId = 1L;
        String status = "friends";
        List<ConnectionsDTO> expectedConnections = new ArrayList<>();
        when(connectionRepository.getConnections(studentId, status)).thenReturn(expectedConnections);

        // Act
        List<ConnectionsDTO> actualConnections = connectionService.getConnections(studentId, status);

        // Assert
        assertEquals(expectedConnections, actualConnections);
    }

    @Test
    void findAll() {
        // Arrange
        List<Connection> expectedConnections = new ArrayList<>();
        when(connectionRepository.findAll()).thenReturn(expectedConnections);

        // Act
        List<Connection> actualConnections = connectionService.findAll();

        // Assert
        assertEquals(expectedConnections, actualConnections);
    }
}