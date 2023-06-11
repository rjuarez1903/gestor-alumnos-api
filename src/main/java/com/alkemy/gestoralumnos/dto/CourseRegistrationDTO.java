package com.alkemy.gestoralumnos.dto;

import com.alkemy.gestoralumnos.models.CourseRegistration;

public class CourseRegistrationDTO {
    private Long id;
    private Long studentId;
    private Long courseId;

    public CourseRegistrationDTO(CourseRegistration courseRegistration) {
        this.id = courseRegistration.getId();
        this.studentId = courseRegistration.getStudent().getId();
        this.courseId = courseRegistration.getCourse().getId();
    }

    @Override
    public String toString() {
        return "CourseRegistrationDTO{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
