package com.alkemy.gestoralumnos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EstudianteDTO {
    private int id;
    private String nombre;
    private String apellido;
    private byte edad;
    private boolean adeudaMaterias;
    private boolean adeudaMatricula;
    private double notaIngreso;
}
