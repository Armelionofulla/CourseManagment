package com.project.courseManagement.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.courseManagement.dto.CourseStudentDTO;
import com.project.courseManagement.entity.Course;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.service.CourseService;
import com.project.courseManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController

    public class CourseController {

    @Autowired()
    CourseService courseService;
    @Autowired()
    StudentService studentService;


    @GetMapping("/courses")
    public HashMap<String, List<Course>> findAll (){

        return courseService.findAll();
    }

    @GetMapping("/courses/{id}")
    public List<CourseStudentDTO> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }


    @GetMapping("/coursesByStudent/me")
    public List<Course> getCourseByStudent(@RequestHeader("username") String username, @RequestHeader("password") String password){
        Student st = studentService.findByUsernameAndPassword(username,password);
        return courseService.findByStudent(st.getId());
    }

    @PostMapping("/coursesByStudent/{id}")
    public List<Course> getCourseByStudentId(@PathVariable long id, @RequestHeader("username") String username, @RequestHeader("password") String password){
        Student st = studentService.findByUsernameAndPassword(username,password);
        return courseService.findByStudent(id);
    }

}

