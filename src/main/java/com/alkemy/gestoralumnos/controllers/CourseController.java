package com.alkemy.gestoralumnos.controllers;

import com.alkemy.gestoralumnos.dto.CourseDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.services.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<StudentDTO>> getStudentsWithHighestGrade(@PathVariable Long id) {
        return courseService.getStudentsWithHighestGrade(id);
    }
    @GetMapping("/{id}/average-age")
    public ResponseEntity<Double> getAverageAge(@PathVariable Long id) {
        return courseService.calculateAverageAge(id);
    }
//
    @PostMapping("/{courseId}/students")
    public ResponseEntity<CourseDTO> addStudent(@PathVariable Long courseId, @RequestParam Long studentId) {
        return courseService.addStudent(courseId, studentId);
    }

    @GetMapping("/{courseId}/students")
    public List<StudentDTO> getRegisteredStudents(@PathVariable Long courseId) {
        return courseService.getRegisteredStudents(courseId);
    }
}

