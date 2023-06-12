package com.alkemy.gestoralumnos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentSaveDTO {
    private final String NAME;
    private final String SURNAME;
    private final int AGE;
    private final boolean HAS_SUBJECT_DEBTS;
    private boolean HAS_ENROLLMENT_DEBT;
    private double ENTRANCE_GRADE;
}
