package com.alkemy.gestoralumnos.dto;

import com.alkemy.gestoralumnos.models.Course;
import lombok.Getter;

@Getter
public class CourseDTO {
    private Long id;
    private String name;

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
    }
}
