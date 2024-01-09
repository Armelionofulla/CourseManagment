package com.project.courseManagement.service;

import com.project.courseManagement.entity.Student;
import com.project.courseManagement.repository.StudentRepository;

import com.project.courseManagement.security.PasswordEnc;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public void save(Student student) {
        studentRepository.save(student);
    }

    public void saveFirstTime(Student student) {
        student.setPassword(PasswordEnc.getSha256(student.getPassword()));
        studentRepository.save(student);
    }


    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(new Student());
    }


    public boolean usernameExists(String username) {
        return studentRepository.existsByUsername(username);
    }

    public boolean emailExists(String email) {
        return studentRepository.existsByEmail(email);
    }


    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findByUsername(String username){
        return  studentRepository.findByUsername(username);
    }



    public Student findByUsernameAndPassword(String username, String password) {
            Student student = studentRepository.findByUsername(username);
              if (student != null) {
                   if (student.getPassword().equals(PasswordEnc.getSha256(password))) {
                       return student;
                   }
              }
              return null;
    }

    public Student updateStudent(Long id,Student student){
        Student std=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found"));
        std.setName(student.getName());
        std.setLastname(student.getLastname());
        std.setEmail(student.getEmail());
        std.setAddress(student.getAddress());
        std.setUsername(student.getUsername());
        std.setPicture(student.getPicture());

        if (student.getPassword().length() > 0) {
            std.setPassword(PasswordEnc.getSha256(student.getPassword()));
        }

        return studentRepository.save(std);
    }




}

