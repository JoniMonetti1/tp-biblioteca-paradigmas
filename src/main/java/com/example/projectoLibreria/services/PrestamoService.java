package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Estudiante;
import com.example.projectoLibreria.models.Prestamo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PrestamoService {
    ResponseEntity<List<Prestamo>> getPrestamos();
    ResponseEntity<Prestamo> getPrestamoById(Long id);
    ResponseEntity<Prestamo> createPrestamo(Long idEstudiante, Long idLibro, int diasDuracion);
    ResponseEntity<Void> deletePrestamoById(Long id);
    ResponseEntity<Void> marcarDevolucion(Long idPrestamo);
}
