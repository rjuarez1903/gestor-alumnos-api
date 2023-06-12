package com.alkemy.gestoralumnos.controllers;

import com.alkemy.gestoralumnos.dto.CourseDTO;
import com.alkemy.gestoralumnos.exceptions.courseExceptions.CourseNotFoundException;
import com.alkemy.gestoralumnos.exceptions.courseExceptions.NoEnrollmentsException;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentAlreadyEnrolledException;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentNotFoundException;
import com.alkemy.gestoralumnos.services.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping
    public List<CourseDTO> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}/students/highest-grade")
    public ResponseEntity<?> getStudentsWithHighestGrade(@PathVariable Long id) throws NoEnrollmentsException, CourseNotFoundException {
        return new ResponseEntity<>(courseService.getStudentsWithHighestGrade(id), HttpStatus.OK);

    }
    @GetMapping("/{id}/average-age")
    public ResponseEntity<?> getAverageAge(@PathVariable Long id) throws NoEnrollmentsException, CourseNotFoundException {
        return ResponseEntity.ok(courseService.calculateAverageAge(id));
    }
//
    @PostMapping("/{courseId}/students")
    public ResponseEntity<?> addStudent(@PathVariable Long courseId, @RequestParam Long studentId) throws StudentAlreadyEnrolledException, CourseNotFoundException, StudentNotFoundException {
        return ResponseEntity.ok(courseService.addStudent(courseId, studentId));
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<?> getRegisteredStudents(@PathVariable Long courseId) throws NoEnrollmentsException, CourseNotFoundException {
        return new ResponseEntity<>(courseService.getRegisteredStudents(courseId), HttpStatus.OK);
    }
}

