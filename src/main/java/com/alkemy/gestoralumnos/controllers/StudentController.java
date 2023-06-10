package com.alkemy.gestoralumnos.controllers;

import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<StudentDTO> get(@PathVariable int id) {
        return studentService.get(id);
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
    public ResponseEntity<StudentDTO> add(@RequestBody StudentDTO student) {
        return studentService.add(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable int id, @RequestBody StudentDTO student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<StudentDTO>> delete(@PathVariable int id) {
        return studentService.delete(id);
    }

}
