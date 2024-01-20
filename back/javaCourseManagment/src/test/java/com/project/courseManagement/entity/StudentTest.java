package com.project.courseManagement.entity;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    @Test
    void setSentInvitations() {
        Set<Connection> expectedSentInvitations = Set.of(new Connection());
        Student student = new Student();
        student.setSentInvitations(expectedSentInvitations);
        assertEquals(expectedSentInvitations, student.getSentInvitations());
    }


}
