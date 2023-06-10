package com.alkemy.gestoralumnos.services.impl;

import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private static List<StudentDTO> students = new ArrayList<>();

    public StudentServiceImpl() {
        addStudents();
    }

    private void addStudents() {
        students.add(new StudentDTO(1, "Juan", "Pérez", (byte) 20, false, true, 8.5));
        students.add(new StudentDTO(2, "María", "Gómez", (byte) 22, false, false, 7.8));
        students.add(new StudentDTO(3, "Pedro", "López", (byte) 19, true, true, 6.2));
        students.add(new StudentDTO(4, "Laura", "Martínez", (byte) 21, true, false, 9.0));
        students.add(new StudentDTO(5, "Carlos", "García", (byte) 18, false, false, 7.2));
        students.add(new StudentDTO(6, "Ana", "Rodríguez", (byte) 20, false, false, 8.8));
        students.add(new StudentDTO(7, "Diego", "Fernández", (byte) 23, false, true, 6.5));
        students.add(new StudentDTO(8, "Sofía", "Hernández", (byte) 19, true, true, 8.0));
        students.add(new StudentDTO(9, "Miguel", "Silva", (byte) 21, false, true, 7.6));
        students.add(new StudentDTO(10, "Julia", "López", (byte) 22, false, false, 9.2));
    }

    public ResponseEntity<StudentDTO> add(StudentDTO student) {
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    public List<StudentDTO> getAll(){
        return students;
    }

    public ResponseEntity<StudentDTO> get(int id) {
        StudentDTO student = students.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if(Objects.isNull(student))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    public ResponseEntity<List<StudentDTO>> delete(int id) {
        if(students.removeIf(student -> student.getId() == id))
            return new ResponseEntity<>(students, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<StudentDTO> update(int id, StudentDTO student) {
        StudentDTO oldStudent = students.stream().filter(empleado -> empleado.getId() == id).findFirst().orElse(null);
        if(Objects.isNull(oldStudent)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        students.remove(oldStudent);
        student.setId(id);
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @Override
    public List<StudentDTO> getDefaulters() {
        return students.stream().filter(StudentDTO::isHasEnrollmentDebt).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsWithSubjectDebts() {
        return students.stream().filter(StudentDTO::isHasSubjectDebts).collect(Collectors.toList());
    }
}
