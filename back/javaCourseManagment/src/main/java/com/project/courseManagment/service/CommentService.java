package com.project.courseManagement.service;

import com.project.courseManagement.controller.CommentController;
import com.project.courseManagement.dto.CommentWithStudentDTO;
import com.project.courseManagement.entity.Comment;
import com.project.courseManagement.entity.Course;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.repository.CommentRepository;
import com.project.courseManagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CourseRepository courseRepository;


    public void create(Student student, Long courseId, Comment comment) {
        Optional course = courseRepository.findById(courseId);
        course.ifPresent(c -> {
            comment.setStudent(student);
            comment.setCourse((Course) c);
            commentRepository.save(comment);
        });
    }
    public List<CommentWithStudentDTO> getByCourseId(Long courseId) {
        return commentRepository.getByCourseId(courseId);
    }

    public void likeComment(Student student, Long commentId) {
        Optional<Comment> comment= commentRepository.findById(commentId);
        comment.ifPresent(c -> {
            String existingLikes = c.getLikes();
            String newLikes;
            if(existingLikes != null && existingLikes.length() == 0) {
                newLikes = student.getId().toString();
            } else {
                newLikes = existingLikes + "," + student.getId().toString();
            }
            c.setLikes(newLikes);

            commentRepository.save(c);
        });
    }
}
