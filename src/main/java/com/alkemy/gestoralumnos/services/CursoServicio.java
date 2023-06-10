package com.alkemy.gestoralumnos.services;

import com.alkemy.gestoralumnos.dto.CursoDTO;
import com.alkemy.gestoralumnos.dto.EstudianteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CursoServicio {

    List<CursoDTO> obtenerTodos();
    ResponseEntity<CursoDTO> cargarAlumno(int id, int idEstudiante);
    ResponseEntity<List<EstudianteDTO>> obtenerAlumnos(int id);
    ResponseEntity<Double> calcularPromedioEdad(int id);
    ResponseEntity<List<EstudianteDTO>> obtenerAlumnosNotaMasAlta(int id);
}