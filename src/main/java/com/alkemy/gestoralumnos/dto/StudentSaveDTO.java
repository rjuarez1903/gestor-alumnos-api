package com.alkemy.gestoralumnos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class StudentSaveDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private int age;
    private String dni;
    private String email;
    private String password;
    private boolean hasSubjectDebts;
    private boolean hasEnrollmentDebt;
    private double entranceGrade;
}
