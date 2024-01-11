package com.project.courseManagement.service;

import com.project.courseManagement.dto.CourseStudentDTO;
import com.project.courseManagement.entity.Course;
import com.project.courseManagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    public HashMap<String , List<Course>> findAll() {
        HashMap<String, List<Course>> courses = new HashMap<>();
        courses.put("major", courseRepository.findMajor());
        courses.put("minor", courseRepository.findMinor());
        return courses;
    }

    public List<CourseStudentDTO> getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> findByStudent(Long id){
        return courseRepository.findByStudent_Id(id);
    }
}
