package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Estudiante;
import com.example.projectoLibreria.models.Prestamo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PrestamoService {
    ResponseEntity<List<Prestamo>> getPrestamos();
    ResponseEntity<Prestamo> getPrestamoById(Long id);
    ResponseEntity<Prestamo> createPrestamo(Estudiante estudiante, Long idLibro);
    ResponseEntity<Void> deletePrestamoById(Long id);
}
