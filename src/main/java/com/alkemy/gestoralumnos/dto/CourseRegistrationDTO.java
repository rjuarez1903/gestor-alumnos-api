package com.alkemy.gestoralumnos.dto;

import com.alkemy.gestoralumnos.models.CourseRegistration;

public class CourseRegistrationDTO {
    private final Long ID;
    private final Long STUDENT_ID;
    private final Long COURSE_ID;

    public CourseRegistrationDTO(CourseRegistration courseRegistration) {
        this.ID = courseRegistration.getId();
        this.STUDENT_ID = courseRegistration.getStudent().getId();
        this.COURSE_ID = courseRegistration.getCourse().getId();
    }

    @Override
    public String toString() {
        return "CourseRegistrationDTO{" +
                "id=" + ID +
                ", studentId=" + STUDENT_ID +
                ", courseId=" + COURSE_ID +
                '}';
    }
}
