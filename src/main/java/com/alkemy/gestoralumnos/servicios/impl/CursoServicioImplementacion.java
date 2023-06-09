package com.alkemy.gestoralumnos.servicios.impl;

import com.alkemy.gestoralumnos.dtos.CursoDTO;
import com.alkemy.gestoralumnos.dtos.EstudianteDTO;
import com.alkemy.gestoralumnos.servicios.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class CursoServicioImplementacion implements CursoServicio {
    private static List<CursoDTO> cursos = new ArrayList<>();
    @Autowired
    private EstudianteServicioImplementacion estudianteServicioImplementacion;

    public CursoServicioImplementacion() {
        cargarCursos();
    }

    private void cargarCursos() {
        cursos.add(new CursoDTO(1, "Programación"));
        cursos.add(new CursoDTO(2, "Robótica"));
    }

    private CursoDTO obtenerCurso(int id) {
        return cursos.stream().filter(c ->  c.getId() == id).findFirst().orElse(null);
    }

    public List<CursoDTO> obtenerTodos() {
        return cursos;
    }

    public ResponseEntity<List<EstudianteDTO>> obtenerAlumnos(int id) {
        CursoDTO curso = obtenerCurso(id);
        if (Objects.isNull(curso))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<EstudianteDTO> estudiantes = curso.getEstudiantes();
        estudiantes.sort((s1, s2) -> s2.getApellido().compareTo(s1.getApellido()));
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CursoDTO> cargarAlumno(int id, int idEstudiante) {
        CursoDTO curso = obtenerCurso(id);
        EstudianteDTO estudiante = estudianteServicioImplementacion.obtenerTodos().stream().filter(e -> e.getId() == idEstudiante).findFirst().orElse(null);
        if (Objects.isNull(curso) || Objects.isNull(estudiante) || curso.getEstudiantes().contains(estudiante))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        curso.agregarEstudiante(estudiante);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Double> calcularPromedioEdad(int id) {
        CursoDTO curso = obtenerCurso(id);
        if (Objects.isNull(curso) || curso.getEstudiantes().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        double promedioEdad = curso.getEstudiantes().stream().mapToInt(EstudianteDTO::getEdad).average().orElse(0);
        return new ResponseEntity<>(promedioEdad, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EstudianteDTO>> obtenerAlumnosNotaMasAlta(int id) {
        CursoDTO curso = obtenerCurso(id);
        if (Objects.isNull(curso) || curso.getEstudiantes().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<EstudianteDTO> estudiantes = curso.getEstudiantes();
        // Acá no me las ingenié para no tener que recorrer dos veces
        OptionalDouble maxNotaIngreso = estudiantes.stream().mapToDouble(EstudianteDTO::getNotaIngreso).max();
        List<EstudianteDTO> estudiantesNotaMaxima = estudiantes.stream().filter(e -> e.getNotaIngreso() == maxNotaIngreso.getAsDouble()).collect(Collectors.toList());
        return new ResponseEntity<>(estudiantesNotaMaxima, HttpStatus.OK);
    }
}
