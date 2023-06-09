package com.alkemy.gestoralumnos.servicios;

import com.alkemy.gestoralumnos.dtos.CursoDTO;
import com.alkemy.gestoralumnos.dtos.EstudianteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CursoServicio {

    public List<CursoDTO> obtenerTodos();
    ResponseEntity<CursoDTO> cargarAlumno(int id, int idEstudiante);
    public ResponseEntity<List<EstudianteDTO>> obtenerAlumnos(int id);
    public ResponseEntity<Double> calcularPromedioEdad(int id);
    public ResponseEntity<List<EstudianteDTO>> obtenerAlumnosNotaMasAlta(int id);
}
