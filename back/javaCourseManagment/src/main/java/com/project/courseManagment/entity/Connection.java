package com.project.courseManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Connection extends Base {

    @Column
    private String status;

    @ManyToOne()
    @JoinColumn(name="receiver_Id", referencedColumnName = "id")
    private Student receiver;
    @ManyToOne()
    @JoinColumn(name="sender_Id", referencedColumnName = "id")
    private Student sender;
}
