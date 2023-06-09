package com.alkemy.gestoralumnos.servicios.impl;

import com.alkemy.gestoralumnos.dtos.EstudianteDTO;
import com.alkemy.gestoralumnos.servicios.EstudianteServicio;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EstudianteServicioImplementacion implements EstudianteServicio {
    private static List<EstudianteDTO> estudiantes = new ArrayList<>();

    public EstudianteServicioImplementacion() {
        cargarEstudiantes();
    }

    private void cargarEstudiantes() {
        estudiantes.add(new EstudianteDTO(1, "Juan", "Pérez", (byte) 20, false, true, 8.5));
        estudiantes.add(new EstudianteDTO(2, "María", "Gómez", (byte) 22, false, false, 7.8));
        estudiantes.add(new EstudianteDTO(3, "Pedro", "López", (byte) 19, true, true, 6.2));
        estudiantes.add(new EstudianteDTO(4, "Laura", "Martínez", (byte) 21, true, false, 9.0));
        estudiantes.add(new EstudianteDTO(5, "Carlos", "García", (byte) 18, false, false, 7.2));
        estudiantes.add(new EstudianteDTO(6, "Ana", "Rodríguez", (byte) 20, false, false, 8.8));
        estudiantes.add(new EstudianteDTO(7, "Diego", "Fernández", (byte) 23, false, true, 6.5));
        estudiantes.add(new EstudianteDTO(8, "Sofía", "Hernández", (byte) 19, true, true, 8.0));
        estudiantes.add(new EstudianteDTO(9, "Miguel", "Silva", (byte) 21, false, true, 7.6));
        estudiantes.add(new EstudianteDTO(10, "Julia", "López", (byte) 22, false, false, 9.2));
    }

    public ResponseEntity<EstudianteDTO> agregar(EstudianteDTO estudiante) {
        estudiantes.add(estudiante);
        return new ResponseEntity<>(estudiante, HttpStatus.CREATED);
    }

    public List<EstudianteDTO> obtenerTodos(){
        return estudiantes;
    }

    public ResponseEntity<EstudianteDTO> obtener(int id) {
        EstudianteDTO estudiante = estudiantes.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        if(Objects.isNull(estudiante))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(estudiante, HttpStatus.OK);
    }

    public ResponseEntity<List<EstudianteDTO>> borrar(int id) {
        if(estudiantes.removeIf(estudiante -> estudiante.getId() == id))
            return new ResponseEntity<>(estudiantes, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<EstudianteDTO> actualizar(int id, EstudianteDTO estudiante) {
        EstudianteDTO estudianteViejo = estudiantes.stream().filter(empleado -> empleado.getId() == id).findFirst().orElse(null);
        if(Objects.isNull(estudianteViejo)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        estudiantes.remove(estudianteViejo);
        estudiante.setId(id);
        estudiantes.add(estudiante);
        return new ResponseEntity<>(estudiante, HttpStatus.OK);
    }

    @Override
    public List<EstudianteDTO> obtenerMorosos() {
        return estudiantes.stream().filter(EstudianteDTO::isAdeudaMatricula).collect(Collectors.toList());
    }

    @Override
    public List<EstudianteDTO> obtenerDeudoresMaterias() {
        return estudiantes.stream().filter(EstudianteDTO::isAdeudaMaterias).collect(Collectors.toList());
    }
}
