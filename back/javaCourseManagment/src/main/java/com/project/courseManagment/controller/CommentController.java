package com.project.courseManagement.controller;


import com.project.courseManagement.dto.CommentWithStudentDTO;
import com.project.courseManagement.entity.Comment;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.service.CommentService;
import com.project.courseManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
public class CommentController {

    @Autowired
    StudentService studentService;

    @Autowired
    CommentService commentService;

    @PostMapping("/comments/{courseId}")
    private void create(@PathVariable Long courseId, @RequestHeader("username") String username, @RequestHeader("password") String password, @RequestBody Comment comment){
        Student student = studentService.findByUsernameAndPassword(username, password);
        commentService.create(student, courseId, comment);
    }

    @GetMapping("/comments/{courseId}")
    private List<CommentWithStudentDTO> getByCourseId(@PathVariable Long courseId, @RequestHeader("username") String username, @RequestHeader("password") String password){
        studentService.findByUsernameAndPassword(username, password);
        return commentService.getByCourseId(courseId);
    }

    @PatchMapping("/comments/{commentId}")
    private void likeComment(@PathVariable Long commentId, @RequestHeader("username") String username, @RequestHeader("password") String password){
        Student student = studentService.findByUsernameAndPassword(username, password);
        commentService.likeComment(student, commentId);
    }

}
