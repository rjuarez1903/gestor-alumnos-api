package com.alkemy.gestoralumnos.services;

import com.alkemy.gestoralumnos.dto.StudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    ResponseEntity<StudentDTO> get(int id);
    ResponseEntity<StudentDTO> add(StudentDTO student);

    List<StudentDTO> getAll();

    ResponseEntity<List<StudentDTO>> delete(int id) throws Exception;

    ResponseEntity<StudentDTO> update(int id, StudentDTO estudiante);

    List<StudentDTO> getDefaulters();

    List<StudentDTO> getStudentsWithSubjectDebts();
}
