package com.alkemy.gestoralumnos.dto;

import com.alkemy.gestoralumnos.models.Course;
import com.alkemy.gestoralumnos.models.CourseRegistration;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CourseDTO {
    private Long id;
    private String name;

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
    }
}
