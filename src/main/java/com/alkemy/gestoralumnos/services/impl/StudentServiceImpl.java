package com.alkemy.gestoralumnos.services.impl;

import com.alkemy.gestoralumnos.dto.StudentSaveDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.exceptions.StudentNotFoundException;
import com.alkemy.gestoralumnos.models.CourseRegistration;
import com.alkemy.gestoralumnos.models.Student;
import com.alkemy.gestoralumnos.repository.CourseRegistrationRepository;
import com.alkemy.gestoralumnos.repository.StudentRepository;
import com.alkemy.gestoralumnos.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public StudentDTO get(Long id) throws StudentNotFoundException {
        return new StudentDTO(getStudent(id));
    }

    @Override
    public StudentDTO add(StudentSaveDTO student) {
        Student st = new Student(student.getNAME(), student.getSURNAME(), student.getAGE(), student.isHAS_SUBJECT_DEBTS(), student.isHAS_ENROLLMENT_DEBT(), student.getENTRANCE_GRADE());
        studentRepository.save(st);
        return new StudentDTO(st);
    }

    public List<StudentDTO> getAll(){
        return studentRepository.findAll().stream().map(StudentDTO::new).toList();
    }

    public List<StudentDTO> delete(Long id) throws StudentNotFoundException {
        Student student = getStudent(id);
        List<CourseRegistration> courseRegistrations = student.getRegistrations();
        courseRegistrationRepository.deleteAll(courseRegistrations);
        studentRepository.delete(student);
        return studentRepository.findAll().stream().map(StudentDTO::new).toList();
    }

    @Override
    public StudentDTO update(Long id, StudentSaveDTO student) throws StudentNotFoundException {
        Student existingStudent = getStudent(id);
        existingStudent.setName(student.getNAME());
        existingStudent.setSurname(student.getSURNAME());
        existingStudent.setAge(student.getAGE());
        existingStudent.setHasSubjectDebts(student.isHAS_SUBJECT_DEBTS());
        existingStudent.setHasEnrollmentDebt(student.isHAS_ENROLLMENT_DEBT());
        existingStudent.setEntranceGrade(student.getENTRANCE_GRADE());
        Student updatedStudent = studentRepository.save(existingStudent);
        return new StudentDTO(updatedStudent);

    }

    @Override
    public List<StudentDTO> getDefaulters() {
        List<Student> students = studentRepository.findAll();
        return students.stream().filter(Student::isHasEnrollmentDebt).map(StudentDTO::new).toList();
    }

    @Override
    public List<StudentDTO> getStudentsWithSubjectDebts() {
        List<Student> students = studentRepository.findAll();
        return students.stream().filter(Student::isHasSubjectDebts).map(StudentDTO::new).toList();
    }

    private Student getStudent(Long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("The student with id " + id + " doesn't exist.");
        }
        return student.get();
    }
}
