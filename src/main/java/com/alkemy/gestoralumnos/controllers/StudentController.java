package com.alkemy.gestoralumnos.controllers;

import com.alkemy.gestoralumnos.dto.StudentSaveDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.exceptions.StudentNotFoundException;
import com.alkemy.gestoralumnos.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping
    public List<StudentDTO> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.get(id));
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/defaulters")
    public List<StudentDTO> getDefaulters() {
        return studentService.getDefaulters();
    }

    @GetMapping("/subject-debts")
    public List<StudentDTO> getStudentsWithSubjectDebts() {
        return studentService.getStudentsWithSubjectDebts();
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentSaveDTO student) {
        return new ResponseEntity<>(studentService.add(student), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StudentSaveDTO student) {
        try {
            StudentDTO updatedStudent = studentService.update(id, student);
            return ResponseEntity.ok(updatedStudent);
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            List<StudentDTO> students = studentService.delete(id);
            return ResponseEntity.ok(students);
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
