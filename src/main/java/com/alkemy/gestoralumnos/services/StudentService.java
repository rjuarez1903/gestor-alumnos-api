package com.alkemy.gestoralumnos.services;

import com.alkemy.gestoralumnos.dto.StudentSaveDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    StudentDTO get(Long id) throws StudentNotFoundException;
    StudentDTO add(StudentSaveDTO student);
    List<StudentDTO> getAll();
    List<StudentDTO> delete(Long id) throws StudentNotFoundException;
    StudentDTO update(Long id, StudentSaveDTO student) throws StudentNotFoundException;
    List<StudentDTO> getDefaulters();
    List<StudentDTO> getStudentsWithSubjectDebts();
}
