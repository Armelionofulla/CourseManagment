package com.project.courseManagement.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateCourseStudentDTOTest {

    @Test
    void testConstructorAndGetters() {
        Long courseId = 123L;
        CreateCourseStudentDTO dto = new CreateCourseStudentDTO();
        dto.courseId = courseId;
        assertEquals(courseId, dto.courseId);
    }
}
