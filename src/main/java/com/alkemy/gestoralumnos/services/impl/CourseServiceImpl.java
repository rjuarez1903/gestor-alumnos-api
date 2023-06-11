package com.alkemy.gestoralumnos.services.impl;

import com.alkemy.gestoralumnos.dto.CourseDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.models.Course;
import com.alkemy.gestoralumnos.models.CourseRegistration;
import com.alkemy.gestoralumnos.models.Student;
import com.alkemy.gestoralumnos.repository.CourseRegistrationRepository;
import com.alkemy.gestoralumnos.repository.CourseRepository;
import com.alkemy.gestoralumnos.repository.StudentRepository;
import com.alkemy.gestoralumnos.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    CourseRegistrationRepository courseRegistrationRepository;
    @Autowired
    private StudentServiceImpl studentService;


//    private void addCourses() {
//        courses.add(new CourseDTO(1, "Spring"));
//        courses.add(new CourseDTO(2, "React"));
//    }

//    private CourseDTO get(int id) {
//        return courses.stream().filter(c ->  c.getId() == id).findFirst().orElse(null);
//    }
//
    public List<CourseDTO> getAll() {
        return courseRepository.findAll().stream().map(CourseDTO::new).toList();
    }

//    public ResponseEntity<List<StudentDTO>> getStudents(int id) {
//        CourseDTO course = get(id);
//        if (Objects.isNull(course))
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        List<StudentDTO> students = course.getStudents();
//        students.sort((s1, s2) -> s2.getSurname().compareTo(s1.getSurname()));
//        return new ResponseEntity<>(students, HttpStatus.OK);
//    }

    public List<StudentDTO> getRegisteredStudents(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (!Objects.isNull(course)) {
            List<CourseRegistration> registrations = course.getRegistrations();
            if (!registrations.isEmpty()) {
                List<Student> registeredStudents = registrations.stream().map(CourseRegistration::getStudent).toList();
                return registeredStudents.stream().map(StudentDTO::new).toList();
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<CourseDTO> addStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);
        if (Objects.isNull(course) || Objects.isNull(student))
            return ResponseEntity.notFound().build();

        boolean isStudentEnrolled = course.getRegistrations().stream()
                .anyMatch(registration -> registration.getStudent().getId().equals(studentId));

        if (isStudentEnrolled) {
            return ResponseEntity.badRequest().build();
        }

        CourseRegistration courseRegistration = new CourseRegistration();
        course.setRegistration(courseRegistration);
        student.setRegistration(courseRegistration);
        courseRegistrationRepository.save(courseRegistration);
        courseRepository.save(course);
        studentRepository.save(student);
        return ResponseEntity.ok(new CourseDTO(course));
    }

    @Override
    public ResponseEntity<Double> calculateAverageAge(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        double averageAge = courseRepository.getAverageAgeByCourseId(id);
        return ResponseEntity.ok(averageAge);
    }

    @Override
    public ResponseEntity<List<StudentDTO>> getStudentsWithHighestGrade(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (Objects.isNull(course) || course.getRegistrations().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        double maxGrade = courseRepository.findHighestEntranceGradeByCourseId(id);
        List<Student> highestGradeStudents = course.getRegistrations()
                .stream()
                .map(CourseRegistration::getStudent)
                .filter(student -> student.getEntranceGrade() == maxGrade)
                .toList();
        return ResponseEntity.ok(highestGradeStudents.stream().map(StudentDTO::new).toList());
    }


}
