package com.alkemy.gestoralumnos.controladores;

import com.alkemy.gestoralumnos.dtos.CursoDTO;
import com.alkemy.gestoralumnos.dtos.EstudianteDTO;
import com.alkemy.gestoralumnos.servicios.impl.CursoServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoControlador {

    @Autowired
    private CursoServicioImplementacion cursoServicioImplementacion;

    @GetMapping
    public List<CursoDTO> obtenerTodos() {
        return cursoServicioImplementacion.obtenerTodos();
    }

    @GetMapping("/{id}/alumnos/nota-mas-alta")
    public ResponseEntity<List<EstudianteDTO>> obtenerAlumnosNotaMasAlta(@PathVariable int id) {
        return cursoServicioImplementacion.obtenerAlumnosNotaMasAlta(id);
    }

    @GetMapping("/{id}/alumnos")
    public ResponseEntity<List<EstudianteDTO>> obtenerAlumnos(@PathVariable int id) {
        return cursoServicioImplementacion.obtenerAlumnos(id);
    }

    @GetMapping("/{id}/promedio-edad")
    public ResponseEntity<Double> obtenerPromedioEdad(@PathVariable int id) {
        return cursoServicioImplementacion.calcularPromedioEdad(id);
    }

    @PostMapping("/{id}/estudiantes")
    public ResponseEntity<CursoDTO> cargarAlumno(@PathVariable int id, @RequestParam int idEstudiante) {
        return cursoServicioImplementacion.cargarAlumno(id, idEstudiante);
    }

}
