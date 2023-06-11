package com.alkemy.gestoralumnos.services;

import com.alkemy.gestoralumnos.dto.StudentSaveDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.models.Student;

import java.util.List;

public interface StudentService {
    StudentDTO get(Long id);
    StudentDTO add(StudentSaveDTO student);
    List<StudentDTO> getAll();
    List<StudentDTO> delete(Long id);
    StudentDTO update(Long id, StudentSaveDTO student);
    List<StudentDTO> getDefaulters();
    List<StudentDTO> getStudentsWithSubjectDebts();
    void save(Student student);
}
