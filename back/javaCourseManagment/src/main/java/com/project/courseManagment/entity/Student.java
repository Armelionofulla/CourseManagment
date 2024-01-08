package com.project.courseManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student extends  Base{
    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String address;

    @Column
    private String password;

    @Lob
    @Column( columnDefinition = "LONGTEXT")
    private String picture;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name="course_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Course> course;

    @JsonIgnore
    @OneToMany(targetEntity=Connection.class, mappedBy="receiver",fetch = FetchType.LAZY)
    private Set<Connection> receivedInvitations;

    @JsonIgnore
    @OneToMany(targetEntity=Connection.class, mappedBy="sender",fetch = FetchType.LAZY)
    private Set<Connection> sentInvitations;
}
