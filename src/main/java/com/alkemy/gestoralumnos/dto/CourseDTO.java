package com.alkemy.gestoralumnos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseDTO {
    private int id;
    private String name;
    private List<StudentDTO> students;

    public CourseDTO(int id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<>();
    }

    public void addStudent(StudentDTO student) {
        students.add(student);
    }
}
