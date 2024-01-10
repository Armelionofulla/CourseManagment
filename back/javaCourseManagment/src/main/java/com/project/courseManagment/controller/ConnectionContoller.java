package com.project.courseManagement.controller;


import com.project.courseManagement.dto.ConnectionsDTO;
import com.project.courseManagement.entity.Connection;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.service.ConnectionService;
import com.project.courseManagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
public class ConnectionContoller {

    @Autowired
    ConnectionService connectionService;

    @Autowired
    StudentService studentService;
    @PostMapping("/add-connections/{id}")
    public ResponseEntity<ErrorResponse> addFriend(@PathVariable Long id, @RequestHeader("username") String username, @RequestHeader("password") String password){
        try {
            Student sender = studentService.findByUsernameAndPassword(username, password);
            connectionService.createConnectionRequest(sender,id);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMyConnections")
    public List<ConnectionsDTO> getMyFriends(@RequestHeader("username") String username, @RequestHeader("password") String password){
            Student sender = studentService.findByUsernameAndPassword(username, password);
                List <ConnectionsDTO> myConnections = connectionService.getConnections(sender.getId(), "friends");
                return myConnections;
    }

    @GetMapping("/getMyPending")
    public List<ConnectionsDTO> getMyPending(@RequestHeader("username") String username, @RequestHeader("password") String password){
        Student sender = studentService.findByUsernameAndPassword(username, password);
        List <ConnectionsDTO> myPending = connectionService.getConnections(sender.getId(), "pending");
        return myPending;
    }
//
    @PatchMapping("/accept-Connections/{id}")
    public ResponseEntity<ErrorResponse> acceptConnection(@PathVariable Long id, @RequestHeader("username") String username, @RequestHeader("password") String password){
        try {
            Student reciever = studentService.findByUsernameAndPassword(username, password);
            connectionService.acceptConection(reciever.getId(), id);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping("/delete-request/{id}")
//    public ResponseEntity<ErrorResponse> deleteConnection(@PathVariable Long id, @RequestHeader("username") String username, @RequestHeader("password") String password){
//        try {
//            Student reciever = studentService.findByUsernameAndPassword(username, password);
//            connectionService.deleteConnection(reciever.getId(), id);
//            return new ResponseEntity<>( HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            ErrorResponse error = new ErrorResponse();
//            error.setMessage(e.getMessage());
//            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
//            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//        }
//    }

}
