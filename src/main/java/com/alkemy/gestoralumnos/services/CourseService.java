package com.alkemy.gestoralumnos.services;

import com.alkemy.gestoralumnos.dto.CourseDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.exceptions.courseExceptions.CourseNotFoundException;
import com.alkemy.gestoralumnos.exceptions.courseExceptions.NoEnrollmentsException;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentAlreadyEnrolledException;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getAll();
    List<StudentDTO> getRegisteredStudents(Long courseId) throws CourseNotFoundException, NoEnrollmentsException;
    ResponseEntity<CourseDTO> addStudent(Long id, Long studentId) throws CourseNotFoundException, StudentNotFoundException, StudentAlreadyEnrolledException;
    ResponseEntity<Double> calculateAverageAge(Long id) throws CourseNotFoundException, NoEnrollmentsException;
    List<StudentDTO> getStudentsWithHighestGrade(Long id) throws CourseNotFoundException, NoEnrollmentsException;
}
