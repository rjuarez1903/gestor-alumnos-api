package com.alkemy.gestoralumnos.services.impl;

import com.alkemy.gestoralumnos.dto.StudentSaveDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentNotFoundException;
import com.alkemy.gestoralumnos.models.CourseRegistration;
import com.alkemy.gestoralumnos.models.Student;
import com.alkemy.gestoralumnos.repository.CourseRegistrationRepository;
import com.alkemy.gestoralumnos.repository.StudentRepository;
import com.alkemy.gestoralumnos.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Student st = new Student(student.getName(), student.getSurname(), student.getAge(), student.isHasSubjectDebts(), student.isHasEnrollmentDebt(), student.getEntranceGrade());
        studentRepository.save(st);
        return new StudentDTO(st);
    }

    public List<StudentDTO> getAll(){
        return studentRepository.findAll().stream().map(StudentDTO::new).toList();
    }

    public ResponseEntity<List<StudentDTO>> delete(Long id) throws StudentNotFoundException {
        Student student = getStudent(id);
        List<CourseRegistration> courseRegistrations = student.getRegistrations();
        courseRegistrationRepository.deleteAll(courseRegistrations);
        studentRepository.delete(student);
        return new ResponseEntity<>(studentRepository.findAll().stream().map(StudentDTO::new).toList(), HttpStatus.OK) ;
    }

    @Override
    public StudentDTO update(Long id, StudentSaveDTO student) throws StudentNotFoundException {
        Student existingStudent = getStudent(id);
        existingStudent.setName(student.getName());
        existingStudent.setSurname(student.getSurname());
        existingStudent.setAge(student.getAge());
        existingStudent.setHasSubjectDebts(student.isHasSubjectDebts());
        existingStudent.setHasEnrollmentDebt(student.isHasEnrollmentDebt());
        existingStudent.setEntranceGrade(student.getEntranceGrade());
        Student updatedStudent = studentRepository.save(existingStudent);
        return new StudentDTO(updatedStudent);
    }

    @Override
    public ResponseEntity<List<StudentDTO>> getDefaulters() {
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students.stream().filter(Student::isHasEnrollmentDebt).map(StudentDTO::new).toList(), HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<List<StudentDTO>> getStudentsWithSubjectDebts() {
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students.stream().filter(Student::isHasSubjectDebts).map(StudentDTO::new).toList(), HttpStatus.OK) ;
    }

    private Student getStudent(Long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("The student with id " + id + " doesn't exist.");
        }
        return student.get();
    }
}
