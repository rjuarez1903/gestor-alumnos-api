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
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GestorAlumnosApplication {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseRegistrationRepository courseRegistrationRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(GestorAlumnosApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData() {
        return (args) -> {

            Student student1 = new Student("Juan", "Pérez", 20, "dni1", "email1@example.com", passwordEncoder.encode("password1"), false, true, 8.5);
            Student student2 = new Student("María", "Gómez", 22, "dni2", "email2@example.com",passwordEncoder.encode("password2"), false, false, 7.8);
            Student student3 = new Student("Pedro", "López", 19, "dni3", "email3@example.com",passwordEncoder.encode("password3"), true, true, 6.2);
            Student student4 = new Student("Laura", "Martínez", 21, "dni4", "email4@example.com",passwordEncoder.encode("password4"), true, false, 9.0);
            Student student5 = new Student("Carlos", "García", 18, "dni5", "email5@example.com", passwordEncoder.encode("password5"), false, false, 7.2);
            Student student6 = new Student("Ana", "Rodríguez", 20, "dni6", "email6@example.com", passwordEncoder.encode("password6"), false, false, 8.8);
            Student student7 = new Student("Diego", "Fernández", 23, "dni7", "email7@example.com", passwordEncoder.encode("password7"), false, true, 6.5);
            Student student8 = new Student("Sofía", "Hernández", 19, "dni8", "email8@example.com", passwordEncoder.encode("password8"), true, true, 8.0);
            Student student9 = new Student("Miguel", "Silva", 21, "dni9", "email9@example.com", passwordEncoder.encode("password9"), false, true, 7.6);
            Student student10 = new Student("Julia", "López", 22, "dni10", "email10@example.com", passwordEncoder.encode("password10"), false, false, 9.2);


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
