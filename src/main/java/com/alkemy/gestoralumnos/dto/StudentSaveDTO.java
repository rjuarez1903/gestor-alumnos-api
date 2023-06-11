package com.alkemy.gestoralumnos.dto;

import lombok.Getter;

@Getter
public class StudentSaveDTO {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private boolean hasSubjectDebts;
    private boolean hasEnrollmentDebt;
    private double entranceGrade;
}
