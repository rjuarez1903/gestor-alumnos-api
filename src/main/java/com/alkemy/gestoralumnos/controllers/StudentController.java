package com.alkemy.gestoralumnos.controllers;

import com.alkemy.gestoralumnos.dto.StudentSaveDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentNotFoundException;
import com.alkemy.gestoralumnos.services.impl.StudentServiceImpl;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> get(@PathVariable Long id) throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.get(id));
    }

    @GetMapping("/defaulters")
    public ResponseEntity<List<StudentDTO>> getDefaulters() {
        return studentService.getDefaulters();
    }

    @GetMapping("/subject-debts")
    public ResponseEntity<List<StudentDTO>> getStudentsWithSubjectDebts() {
        return studentService.getStudentsWithSubjectDebts();
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody @Valid StudentSaveDTO student) {
        return new ResponseEntity<>(studentService.add(student), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StudentSaveDTO student) throws StudentNotFoundException {
        return new ResponseEntity<>(studentService.update(id, student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws StudentNotFoundException {
        return studentService.delete(id);
    }

}
