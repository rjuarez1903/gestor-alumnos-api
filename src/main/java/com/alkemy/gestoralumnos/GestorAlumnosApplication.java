package com.alkemy.gestoralumnos;

import com.alkemy.gestoralumnos.dto.CourseRegistrationDTO;
import com.alkemy.gestoralumnos.dto.StudentDTO;
import com.alkemy.gestoralumnos.models.Course;
import com.alkemy.gestoralumnos.models.CourseRegistration;
import com.alkemy.gestoralumnos.models.Student;
import com.alkemy.gestoralumnos.repository.CourseRegistrationRepository;
import com.alkemy.gestoralumnos.repository.CourseRepository;
import com.alkemy.gestoralumnos.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GestorAlumnosApplication {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseRegistrationRepository courseRegistrationRepository;
    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestorAlumnosApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData() {
        return (args) -> {

            Student student1 = new Student("Juan", "Pérez", (byte) 20, false, true, 8.5);
            Student student2 =new Student("María", "Gómez", (byte) 22, false, false, 7.8);
            Student student3 = new Student("Pedro", "López", (byte) 19, true, true, 6.2);
            Student student4 = new Student("Laura", "Martínez", (byte) 21, true, false, 9.0);
            Student student5 = new Student("Carlos", "García", (byte) 18, false, false, 7.2);
            Student student6 = new Student("Ana", "Rodríguez", (byte) 20, false, false, 8.8);
            Student student7 = new Student("Diego", "Fernández", (byte) 23, false, true, 6.5);
            Student student8 = new Student("Sofía", "Hernández", (byte) 19, true, true, 8.0);
            Student student9 = new Student("Miguel", "Silva", (byte) 21, false, true, 7.6);
            Student student10 =  new Student("Julia", "López", (byte) 22, false, false, 9.2);

            Course course1 = new Course("React");
            Course course2 = new Course("Java");

            CourseRegistration courseRegistration1 = new CourseRegistration();
            student1.setRegistration(courseRegistration1);
            course1.setRegistration(courseRegistration1);
            CourseRegistration courseRegistration2 = new CourseRegistration();
            student1.setRegistration(courseRegistration2);
            course2.setRegistration(courseRegistration2);

            studentRepository.saveAll(List.of(student1, student2, student3, student4, student5, student6, student7, student8, student9, student10));
            courseRepository.saveAll(List.of(course1, course2));
            courseRegistrationRepository.saveAll(List.of(courseRegistration1, courseRegistration2));

        };
    }
}
