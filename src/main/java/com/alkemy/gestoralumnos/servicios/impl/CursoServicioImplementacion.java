package com.alkemy.gestoralumnos.servicios.impl;

import com.alkemy.gestoralumnos.dtos.EstudianteDTO;
import com.alkemy.gestoralumnos.servicios.CursoServicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoServicioImplementacion implements CursoServicio {
    private static List<EstudianteDTO> estudiantes = new ArrayList<>();
}
