package com.project.courseManagement.controller;

import com.project.courseManagement.LoginObject;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController

public class StudentController {

    @Autowired
    StudentService studentService;


    @PostMapping("/register")
    public ResponseEntity<ErrorResponse> save(@Valid @RequestBody Student student) {
        try {
            if(studentService.usernameExists(student.getUsername())){
                ErrorResponse error = new ErrorResponse();
                error.setMessage("Username already exists");
                error.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            } else if (studentService.emailExists(student.getEmail())) {
                ErrorResponse error = new ErrorResponse();
                error.setMessage("Email already exists");
                error.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            studentService.saveFirstTime(student);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/getStudent/me")
    public Student findById(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        return studentService.findByUsernameAndPassword(username, password);
    }

    @GetMapping("/students/{id}")
    public Student getById(@PathVariable Long id,@RequestHeader("username") String username, @RequestHeader("password") String password) {
        Student student = studentService.findByUsernameAndPassword(username, password);
        return studentService.findById(id);
    }

    @GetMapping("/getStudents")
    public List<Student> findAll(){

        return studentService.findAll();
    }
  /*  @PutMapping("/edit/{id}")
    public Student edit(@RequestBody Student student){

    }*/

    @PostMapping("/login")
    public ResponseEntity<ErrorResponse> login(@Valid @RequestBody  LoginObject loginObject) {
        try {
            Student student = studentService.findByUsernameAndPassword(loginObject.getUsername(), loginObject.getPassword());
            if(student == null){
                ErrorResponse error = new ErrorResponse();
                error.setMessage("Wrong credencials");
                error.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>( HttpStatus.OK);
            }catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateStudent/me")
    public ResponseEntity<ErrorResponse> updateStudent(@RequestHeader("username") String username, @RequestHeader("password") String password,@RequestBody Student student) {
        try {
            Student st = studentService.findByUsernameAndPassword(username, password);
            studentService.updateStudent(st.getId(), student);
            return new ResponseEntity<>( HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
