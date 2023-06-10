package com.alkemy.gestoralumnos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private int id;
    private String name;
    private String surname;
    private int age;
    private boolean hasSubjectDebts;
    private boolean hasEnrollmentDebt;
    private double entranceGrade;
}
