package com.project.courseManagement.repository;

import com.project.courseManagement.dto.CourseStudentDTO;
import com.project.courseManagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("FROM Course WHERE type = 'major' ")
    List<Course> findMajor();
    @Query("FROM Course WHERE type = 'minor' ")
    List<Course> findMinor();


    @Query(value = "Select c.name as courseName, c.start_date as startDate, c.end_date as endDate, c.type, c.image, c.id," +
            " c.course_time as courseTime, c.lecture_hall as lectureHall," +
            " s.name as studentName, s.lastName,  s.username, s.email, s.address, s.picture, s.id as studentId" +
            " FROM course c " +
            " LEFT JOIN course_student cs on cs.course_id = c.id" +
            " LEFT JOIN student s on cs.student_id = s.id where c.id = :id ", nativeQuery = true)
    List<CourseStudentDTO> getCourseById(@Param("id") Long id);

    List<Course> findByStudent_Id(Long id);




}
