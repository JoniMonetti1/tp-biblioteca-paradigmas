package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.models.Estudiante;
import com.example.projectoLibreria.models.Prestamo;
import com.example.projectoLibreria.repositories.PrestamoRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class PrestamoServiceImpl implements PrestamoService{
    private final PrestamoRepository prestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public ResponseEntity<List<Prestamo>> getPrestamos() {
        List<Prestamo> result = prestamoRepository.findAll();
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Prestamo> getPrestamoById(Long id) {
        Optional<Prestamo> optionalPrestamo = prestamoRepository.findById(id);
        return optionalPrestamo.isPresent()? ResponseEntity.ok(optionalPrestamo.get()) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Prestamo> createPrestamo(Estudiante estudiante, Long idLibro) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePrestamoById(Long id) {
        return null;
    }
}
