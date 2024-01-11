package com.project.courseManagement.repository;

import com.project.courseManagement.dto.CommentWithStudentDTO;
import com.project.courseManagement.dto.ConnectionsDTO;
import com.project.courseManagement.entity.Comment;
import com.project.courseManagement.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "Select c.id, c.likes, c.text, c.student_id as studentId, s.username from comment c JOIN student s on s.id = c.student_id where course_id = :courseId" , nativeQuery = true)
    List<CommentWithStudentDTO> getByCourseId(Long courseId);

    @Override
    Optional<Comment> findById(Long aLong);
}
