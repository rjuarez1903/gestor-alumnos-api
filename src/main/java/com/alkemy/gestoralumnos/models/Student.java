package com.alkemy.gestoralumnos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name="students")
@Entity(name="Student")
@NoArgsConstructor
@Getter
public class Student {
    @Id
    @Column(name = "student_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private int age;
    private boolean hasSubjectDebts;
    private boolean hasEnrollmentDebt;
    private double entranceGrade;
    @OneToMany(mappedBy = "student")
    List<CourseRegistration> registrations;

    public Student(String name, String surname, int age, boolean hasSubjectDebts, boolean hasEnrollmentDebt, double entranceGrade) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.hasSubjectDebts = hasSubjectDebts;
        this.hasEnrollmentDebt = hasEnrollmentDebt;
        this.entranceGrade = entranceGrade;
        registrations = new ArrayList<>();
    }

    public void setRegistration(CourseRegistration registration) {
        registration.setStudent(this);
        registrations.add(registration);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHasSubjectDebts(boolean hasSubjectDebts) {
        this.hasSubjectDebts = hasSubjectDebts;
    }

    public void setHasEnrollmentDebt(boolean hasEnrollmentDebt) {
        this.hasEnrollmentDebt = hasEnrollmentDebt;
    }

    public void setEntranceGrade(double entranceGrade) {
        this.entranceGrade = entranceGrade;
    }

    public void setRegistrations(List<CourseRegistration> registrations) {
        this.registrations = registrations;
    }
}
