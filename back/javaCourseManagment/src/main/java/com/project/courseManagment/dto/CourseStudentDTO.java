package com.project.courseManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;

import java.util.Date;


public interface CourseStudentDTO {
    String  getCourseName();
    Date getStartDate();
     Date getEndDate();

     String getType();

    String getImage();

   long getId();

   String getCourseTime();

   String getLectureHall();

   String getStudentName();
   String getLastname();


    String getUsername();

    String getEmail();

    String getAddress();

    String getPicture();

    Long getStudentId();
}