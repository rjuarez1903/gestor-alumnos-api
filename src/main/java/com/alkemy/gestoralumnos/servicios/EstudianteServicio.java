package com.alkemy.gestoralumnos.servicios;

import com.alkemy.gestoralumnos.dtos.EstudianteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstudianteServicio {
    ResponseEntity<EstudianteDTO> agregar(EstudianteDTO student);

    List<EstudianteDTO> obtenerTodos();

    ResponseEntity<List<EstudianteDTO>> borrar(int id) throws Exception;

    ResponseEntity<EstudianteDTO> actualizar(int id, EstudianteDTO estudiante);
}
