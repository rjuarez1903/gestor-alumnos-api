package com.alkemy.gestoralumnos.controllers;

import com.alkemy.gestoralumnos.dto.CourseDTO;
import com.alkemy.gestoralumnos.exceptions.CourseNotFoundException;
import com.alkemy.gestoralumnos.exceptions.NoEnrollmentsException;
import com.alkemy.gestoralumnos.exceptions.StudentAlreadyEnrolledException;
import com.alkemy.gestoralumnos.exceptions.StudentNotFoundException;
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
    public ResponseEntity<?> getStudentsWithHighestGrade(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(courseService.getStudentsWithHighestGrade(id));
        } catch (CourseNotFoundException | NoEnrollmentsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}/average-age")
    public ResponseEntity<?> getAverageAge(@PathVariable Long id) {
        try {
            return courseService.calculateAverageAge(id);
        } catch (CourseNotFoundException | NoEnrollmentsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
//
    @PostMapping("/{courseId}/students")
    public ResponseEntity<?> addStudent(@PathVariable Long courseId, @RequestParam Long studentId) {
        try {
            return courseService.addStudent(courseId, studentId);
        } catch (CourseNotFoundException | StudentNotFoundException | StudentAlreadyEnrolledException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<?> getRegisteredStudents(@PathVariable Long courseId) {
        try {
            return new ResponseEntity<>(courseService.getRegisteredStudents(courseId), HttpStatus.OK);
        } catch (CourseNotFoundException | NoEnrollmentsException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

