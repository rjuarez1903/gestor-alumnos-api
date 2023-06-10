package com.alkemy.gestoralumnos.services.impl;

import com.alkemy.gestoralumnos.dto.CourseDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private static List<CourseDTO> courses = new ArrayList<>();
    @Autowired
    private StudentServiceImpl studentService;

    public CourseServiceImpl() {
        addCourses();
    }

    private void addCourses() {
        courses.add(new CourseDTO(1, "Spring"));
        courses.add(new CourseDTO(2, "React"));
    }

    private CourseDTO get(int id) {
        return courses.stream().filter(c ->  c.getId() == id).findFirst().orElse(null);
    }

    public List<CourseDTO> getAll() {
        return courses;
    }

    public ResponseEntity<List<StudentDTO>> getStudents(int id) {
        CourseDTO course = get(id);
        if (Objects.isNull(course))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<StudentDTO> students = course.getStudents();
        students.sort((s1, s2) -> s2.getSurname().compareTo(s1.getSurname()));
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CourseDTO> addStudent(int id, int studentId) {
        CourseDTO course = get(id);
        StudentDTO student = studentService.getAll().stream().filter(e -> e.getId() == studentId).findFirst().orElse(null);
        if (Objects.isNull(course) || Objects.isNull(student) || course.getStudents().contains(student))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        course.addStudent(student);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Double> calculateAverageAge(int id) {
        CourseDTO course = get(id);
        if (Objects.isNull(course) || course.getStudents().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        double averageAge = course.getStudents().stream().mapToInt(StudentDTO::getAge).average().orElse(0);
        return new ResponseEntity<>(averageAge, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<StudentDTO>> getStudentsWithHighestGrade(int id) {
        CourseDTO course = get(id);
        if (Objects.isNull(course) || course.getStudents().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<StudentDTO> students = course.getStudents();
        // Acá no me las ingenié para no tener que recorrer dos veces
        OptionalDouble highestGrade = students.stream().mapToDouble(StudentDTO::getEntranceGrade).max();
        List<StudentDTO> highestGradeStudents = students.stream().filter(e -> e.getEntranceGrade() == highestGrade.getAsDouble()).collect(Collectors.toList());
        return new ResponseEntity<>(highestGradeStudents, HttpStatus.OK);
    }
}
