package com.alkemy.gestoralumnos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="course_registration")
@Entity(name="CourseRegistration")
@NoArgsConstructor
@Getter
public class CourseRegistration {
    @Id
    @Column(name = "course_registration_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;
    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
