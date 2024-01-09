package com.project.courseManagement.repository;

import com.project.courseManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByCourse_Id(Long id);

    Student findByUsername(String username);

    Student findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<Student> findAll();

    List<Student> findByCourse_Id(Long id);
}

