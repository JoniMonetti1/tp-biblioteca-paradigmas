package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Estudiante;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstudianteService {
    ResponseEntity<List<Estudiante>> getEstudiantes();
    ResponseEntity<Estudiante> getEstudianteById(Long id);
    ResponseEntity<Estudiante> createEstudiante(Estudiante estudiante);
    ResponseEntity<Estudiante> updateEstudiante(Long id, Estudiante estudiante);
    ResponseEntity<Void> deleteEstudiante(Long id);
}
