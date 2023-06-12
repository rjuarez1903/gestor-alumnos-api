package com.alkemy.gestoralumnos.services.impl;

import com.alkemy.gestoralumnos.dto.CourseDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.exceptions.courseExceptions.CourseNotFoundException;
import com.alkemy.gestoralumnos.exceptions.courseExceptions.NoEnrollmentsException;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentAlreadyEnrolledException;
import com.alkemy.gestoralumnos.exceptions.studentExceptions.StudentNotFoundException;
import com.alkemy.gestoralumnos.models.Course;
import com.alkemy.gestoralumnos.models.CourseRegistration;
import com.alkemy.gestoralumnos.models.Student;
import com.alkemy.gestoralumnos.repository.CourseRegistrationRepository;
import com.alkemy.gestoralumnos.repository.CourseRepository;
import com.alkemy.gestoralumnos.repository.StudentRepository;
import com.alkemy.gestoralumnos.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    CourseRegistrationRepository courseRegistrationRepository;

    public List<CourseDTO> getAll() {
        return courseRepository.findAll().stream().map(CourseDTO::new).toList();
    }

    public List<StudentDTO> getRegisteredStudents(Long courseId) throws CourseNotFoundException, NoEnrollmentsException {
        Course course = getCourse(courseId);
        List<CourseRegistration> registrations = course.getRegistrations();
        if (registrations.isEmpty()) {
            throw new NoEnrollmentsException("The course with id " + courseId + " has no enrollments.");
        }
        List<Student> registeredStudents = registrations.stream().map(CourseRegistration::getStudent).toList();
        return registeredStudents.stream().map(StudentDTO::new).toList();
    }

    @Override
    public CourseDTO addStudent(Long courseId, Long studentId) throws CourseNotFoundException, StudentNotFoundException, StudentAlreadyEnrolledException {
        Course course = getCourse(courseId);
        Student student = getStudent(studentId);
        boolean isStudentEnrolled = course.getRegistrations().stream()
                .anyMatch(registration -> registration.getStudent().getId().equals(studentId));
        if (isStudentEnrolled) {
            throw new StudentAlreadyEnrolledException("The student with id " + studentId + " is already enrolled in the course with id " + courseId + ".");
        }
        CourseRegistration courseRegistration = new CourseRegistration();
        course.setRegistration(courseRegistration);
        student.setRegistration(courseRegistration);
        courseRegistrationRepository.save(courseRegistration);
        courseRepository.save(course);
        studentRepository.save(student);
        return new CourseDTO(course);
    }

    @Override
    public double calculateAverageAge(Long id) throws CourseNotFoundException, NoEnrollmentsException {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            if (!course.get().getRegistrations().isEmpty()) {
                return  courseRepository.getAverageAgeByCourseId(id);
            }
            throw new NoEnrollmentsException("The course with id " + id + " has no enrollments.");
        }
        throw new CourseNotFoundException("The course with id " + id + " doesn't exist.");
    }

    @Override
    public List<StudentDTO> getStudentsWithHighestGrade(Long id) throws CourseNotFoundException, NoEnrollmentsException {
        Course course = getCourse(id);
        if (course.getRegistrations().isEmpty()) {
            throw new NoEnrollmentsException("The course with id " + id + " has no enrollments.");
        }
        double maxGrade = courseRepository.findHighestEntranceGradeByCourseId(id);
        List<Student> highestGradeStudents = course.getRegistrations()
                .stream()
                .map(CourseRegistration::getStudent)
                .filter(student -> student.getEntranceGrade() == maxGrade)
                .toList();
        return highestGradeStudents.stream().map(StudentDTO::new).toList();
    }

    private Course getCourse(Long id) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new CourseNotFoundException("The course with id " + id + " doesn't exist.");
        }
        return course.get();
    }

    private Student getStudent(Long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("The student with id " + id + " doesn't exist.");
        }
        return student.get();
    }
}
