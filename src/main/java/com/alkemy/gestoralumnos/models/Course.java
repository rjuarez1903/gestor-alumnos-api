package com.alkemy.gestoralumnos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name="courses")
@Entity(name="Course")
@NoArgsConstructor
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "course")
    List<CourseRegistration> registrations;

    public Course(String name) {
        this.name = name;
        registrations = new ArrayList<>();
    }

    public void setRegistration(CourseRegistration registration) {
        registration.setCourse(this);
        registrations.add(registration);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistrations(List<CourseRegistration> registrations) {
        this.registrations = registrations;
    }

}
