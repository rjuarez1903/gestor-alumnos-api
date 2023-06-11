package com.alkemy.gestoralumnos.services;

import com.alkemy.gestoralumnos.dto.CourseDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getAll();
    List<StudentDTO> getRegisteredStudents(Long courseId);
    ResponseEntity<CourseDTO> addStudent(Long id, Long studentId);
    ResponseEntity<Double> calculateAverageAge(Long id);
    ResponseEntity<List<StudentDTO>> getStudentsWithHighestGrade(Long id);
}
