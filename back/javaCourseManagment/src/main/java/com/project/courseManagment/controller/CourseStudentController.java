package com.project.courseManagement.controller;

import com.project.courseManagement.dto.CreateCourseStudentDTO;
import com.project.courseManagement.entity.Course;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.service.CourseService;
import com.project.courseManagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class CourseStudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;



    @PostMapping("/course-student")
    public ResponseEntity save(@Valid @RequestBody CreateCourseStudentDTO createCourseStudent) {
        Student student = studentService.findByUsernameAndPassword(createCourseStudent.username, createCourseStudent.password);

         if(student != null) {
             Optional<Course> course = courseService.findById(createCourseStudent.courseId);
             course.ifPresent(c -> {
                 Set<Course> existingCourses = student.getCourse();
                 existingCourses.add(c);
                 student.setCourse(existingCourses);
                 studentService.save(student);
             });
             return new ResponseEntity<>("Success", HttpStatus.CREATED);
         }
        ErrorResponse error = new ErrorResponse();
        error.setMessage("Failed to join course");
        error.setErrorCode(404);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/course-remove")
    public ResponseEntity delete(@Valid @RequestBody CreateCourseStudentDTO createCourseStudent){
        Student student = studentService.findByUsernameAndPassword(createCourseStudent.username, createCourseStudent.password);
        if(student != null) {
            Optional<Course> course = courseService.findById(createCourseStudent.courseId);
            course.ifPresent(c -> {
                Set<Course> existingCourses =student.getCourse();
                existingCourses.remove(c);
                student.setCourse(existingCourses);
                studentService.save(student);
            });
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }
        ErrorResponse error = new ErrorResponse();
        error.setMessage("Failed to delete course");
        error.setErrorCode(404);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
}

}
