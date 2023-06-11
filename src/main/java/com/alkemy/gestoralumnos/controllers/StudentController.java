package com.alkemy.gestoralumnos.controllers;

import com.alkemy.gestoralumnos.dto.StudentSaveDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<StudentDTO> get(@PathVariable Long id) {
        StudentDTO student = studentService.get(id);
        if (Objects.isNull(student))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(student);
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
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentSaveDTO student) {
        StudentDTO updatedStudent = studentService.update(id, student);
        if (Objects.isNull(updatedStudent))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<StudentDTO>> delete(@PathVariable Long id) {
        List<StudentDTO> students = studentService.delete(id);
        if (Objects.isNull(students))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(students);
    }

}
