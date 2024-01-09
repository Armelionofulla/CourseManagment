package com.project.courseManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course extends Base {
    @Column
    private String name;

    @Column
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column
    private String type;

    @Column
    private String image;

    @Column
    private Time course_time;

    @Column
    private String lecture_hall;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "course")
    @JsonIgnore
    private Set<Student> student;



}
