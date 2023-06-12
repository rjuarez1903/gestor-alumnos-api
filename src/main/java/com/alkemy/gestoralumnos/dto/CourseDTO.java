package com.alkemy.gestoralumnos.dto;

import com.alkemy.gestoralumnos.models.Course;
import lombok.Getter;

@Getter
public class CourseDTO {
    private final Long ID;
    private final String NAME;

    public CourseDTO(Course course) {
        this.ID = course.getId();
        this.NAME = course.getName();
    }
}
