package com.project.courseManagement;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@CrossOrigin
public class LoginObject {
    private String username;
    private String password;
}
