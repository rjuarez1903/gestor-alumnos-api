package com.alkemy.gestoralumnos.dto;

import com.alkemy.gestoralumnos.models.Student;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private String email;
    private String password;
    private int age;
    private boolean hasSubjectDebts;
    private boolean hasEnrollmentDebt;
    private double entranceGrade;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.surname = student.getSurname();
        this.dni = student.getDni();
        this.email = student.getEmail();
        this.password = student.getPassword();
        this.age = student.getAge();
        this.hasSubjectDebts = student.isHasSubjectDebts();
        this.hasEnrollmentDebt = student.isHasEnrollmentDebt();
        this.entranceGrade = student.getEntranceGrade();
    }
}
