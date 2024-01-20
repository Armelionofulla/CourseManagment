package com.project.courseManagement.controller;

import com.project.courseManagement.dto.ConnectionsDTO;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.service.ConnectionService;
import com.project.courseManagement.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConnectionContollerTest {

    @Mock
    private ConnectionService connectionService;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private ConnectionContoller connectionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddFriend_Success() {
        Long connectionId = 1L;
        String username = "testUser";
        String password = "testPassword";

        Student sender = new Student();
        sender.setId(1L);

        when(studentService.findByUsernameAndPassword(username, password)).thenReturn(sender);


        ResponseEntity<ErrorResponse> responseEntity = connectionController.addFriend(connectionId, username, password);

        verify(connectionService, times(1)).createConnectionRequest(sender, connectionId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testAddFriend_InvalidInput() {

        Long connectionId = 1L;
        String username = "testUser";
        String password = "testPassword";

        when(studentService.findByUsernameAndPassword(username, password)).thenThrow(new IllegalArgumentException("Invalid input"));

        ResponseEntity<ErrorResponse> responseEntity = connectionController.addFriend(connectionId, username, password);

        verify(connectionService, never()).createConnectionRequest(any(), anyLong());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid input", responseEntity.getBody().getMessage());
    }

    @Test
    void testGetMyFriends() {

        String username = "testUser";
        String password = "testPassword";

        Student sender = new Student();
        sender.setId(1L);

        List<ConnectionsDTO> connectionsDTOList = new ArrayList<>();

        when(studentService.findByUsernameAndPassword(username, password)).thenReturn(sender);
        when(connectionService.getConnections(sender.getId(), "friends")).thenReturn(connectionsDTOList);


        List<ConnectionsDTO> result = connectionController.getMyFriends(username, password);


        assertEquals(connectionsDTOList, result);
    }

    @Test
    void testGetMyPending() {

        String username = "testUser";
        String password = "testPassword";

        Student sender = new Student();
        sender.setId(1L);

        List<ConnectionsDTO> connectionsDTOList = new ArrayList<>();

        when(studentService.findByUsernameAndPassword(username, password)).thenReturn(sender);
        when(connectionService.getConnections(sender.getId(), "pending")).thenReturn(connectionsDTOList);


        List<ConnectionsDTO> result = connectionController.getMyPending(username, password);


        assertEquals(connectionsDTOList, result);
    }

    @Test
    void testAcceptConnection_Success() {

        Long connectionId = 1L;
        String username = "testUser";
        String password = "testPassword";

        Student receiver = new Student();
        receiver.setId(2L);

        when(studentService.findByUsernameAndPassword(username, password)).thenReturn(receiver);

        // Act
        ResponseEntity<ErrorResponse> responseEntity = connectionController.acceptConnection(connectionId, username, password);

        // Assert
        verify(connectionService, times(1)).acceptConection(receiver.getId(), connectionId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testAcceptConnection_InvalidInput() {
        // Arrange
        Long connectionId = 1L;
        String username = "testUser";
        String password = "testPassword";

        when(studentService.findByUsernameAndPassword(username, password)).thenThrow(new IllegalArgumentException("Invalid input"));

        // Act
        ResponseEntity<ErrorResponse> responseEntity = connectionController.acceptConnection(connectionId, username, password);

        // Assert
        verify(connectionService, never()).acceptConection(anyLong(), anyLong());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid input", responseEntity.getBody().getMessage());
    }
}
