package com.alkemy.gestoralumnos.controllers;

import com.alkemy.gestoralumnos.dto.StudentSaveDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentNotFoundException;
import com.alkemy.gestoralumnos.models.Student;
import com.alkemy.gestoralumnos.services.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
        return ResponseEntity.ok(studentService.getDefaulters());
    }

    @GetMapping("/subject-debts")
    public ResponseEntity<List<StudentDTO>> getStudentsWithSubjectDebts() {
        return ResponseEntity.ok(studentService.getStudentsWithSubjectDebts());
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody @Valid StudentSaveDTO student) {
        StudentDTO st = studentService.add(student);
        return ResponseEntity
                .created(URI.create("/api/students/" + st.getId()))
                .body(st);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StudentSaveDTO student) throws StudentNotFoundException {
        return new ResponseEntity<>(studentService.update(id, student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.delete(id));
    }

}
