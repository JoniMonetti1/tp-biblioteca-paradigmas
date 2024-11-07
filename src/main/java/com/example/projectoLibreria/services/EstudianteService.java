package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Estudiante;
import com.example.projectoLibreria.models.PrestamoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstudianteService {
    ResponseEntity<List<Estudiante>> getEstudiantes();
    ResponseEntity<Estudiante> getEstudianteById(Long id);
    ResponseEntity<Estudiante> createEstudiante(Estudiante estudiante);
    ResponseEntity<Void> deleteEstudiante(Long id);
    ResponseEntity<List<PrestamoDTO>> obtenerPrestamosPorEstudiante(Long idEstudiante);
}
