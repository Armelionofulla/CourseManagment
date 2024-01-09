package com.project.courseManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment extends Base {

    @ManyToOne()
    @JoinColumn(name="student_id", referencedColumnName = "id")
    private Student student;
    @ManyToOne()
    @JoinColumn(name="course_id", referencedColumnName = "id")
    private Course course;

    @Column
    private String likes;

    @Column
    private String text;
}
