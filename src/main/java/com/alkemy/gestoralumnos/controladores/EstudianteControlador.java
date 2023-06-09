package com.alkemy.gestoralumnos.controladores;

import com.alkemy.gestoralumnos.dtos.EstudianteDTO;
import com.alkemy.gestoralumnos.servicios.impl.EstudianteServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteControlador {

    @Autowired
    private EstudianteServicioImplementacion estudianteServicioImplementacion;

    @GetMapping
    public List<EstudianteDTO> obtenerTodos() {
        return estudianteServicioImplementacion.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtener(@PathVariable int id) {
        return estudianteServicioImplementacion.obtener(id);
    }

    @GetMapping("/morosos")
    public List<EstudianteDTO>  obtenerMorosos() {
        return estudianteServicioImplementacion.obtenerMorosos();
    }

    @GetMapping("/deudores-materias")
    public List<EstudianteDTO>  obtenerDeudoresMaterias() {
        return estudianteServicioImplementacion.obtenerDeudoresMaterias();
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> agregar(@RequestBody EstudianteDTO estudiante) {
        return estudianteServicioImplementacion.agregar(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizar(@PathVariable int id, @RequestBody EstudianteDTO estudiante)  {
        return estudianteServicioImplementacion.actualizar(id, estudiante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<EstudianteDTO>> borrar(@PathVariable int id) {
        return estudianteServicioImplementacion.borrar(id);
    }

}
