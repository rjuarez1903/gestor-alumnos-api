package com.alkemy.gestoralumnos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CursoDTO {
    int id;
    String name;
    List<EstudianteDTO> estudiantes;

    public CursoDTO(int id, String name) {
        this.id = id;
        this.name = name;
        this.estudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(EstudianteDTO estudiante) {
        estudiantes.add(estudiante);
    }
}
