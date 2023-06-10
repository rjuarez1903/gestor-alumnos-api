package com.alkemy.gestoralumnos.services;

import com.alkemy.gestoralumnos.dto.CourseDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getAll();
    ResponseEntity<CourseDTO> addStudent(int id, int studentId);
    ResponseEntity<List<StudentDTO>> getStudents(int id);
    ResponseEntity<Double> calculateAverageAge(int id);
    ResponseEntity<List<StudentDTO>> getStudentsWithHighestGrade(int id);
}
