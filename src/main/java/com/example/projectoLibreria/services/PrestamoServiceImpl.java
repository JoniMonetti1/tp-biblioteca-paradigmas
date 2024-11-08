package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.models.Estudiante;
import com.example.projectoLibreria.models.Prestamo;
import com.example.projectoLibreria.repositories.EjemplarRepository;
import com.example.projectoLibreria.repositories.EstudianteRepository;
import com.example.projectoLibreria.repositories.PrestamoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final EjemplarRepository ejemplarRepository;
    private final EstudianteRepository estudianteRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, EjemplarRepository ejemplarRepository, EstudianteRepository estudianteRepository) {
        this.prestamoRepository = prestamoRepository;
        this.ejemplarRepository = ejemplarRepository;
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public ResponseEntity<List<Prestamo>> getPrestamos() {
        List<Prestamo> result = prestamoRepository.findAll();
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Prestamo> getPrestamoById(Long id) {
        Optional<Prestamo> optionalPrestamo = prestamoRepository.findById(id);
        return optionalPrestamo.isPresent() ? ResponseEntity.ok(optionalPrestamo.get()) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Prestamo> createPrestamo(Long idEstudiante, Long idLibro, int diasDuracion) {
        Optional<Ejemplar> optionalEjemplar = ejemplarRepository.obtenerEjemplarDisponible(idLibro);
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(idEstudiante);

        if (optionalEjemplar.isPresent() && optionalEstudiante.isPresent()) {
            Estudiante estudiante = optionalEstudiante.get();
            Ejemplar ejemplar = optionalEjemplar.get();

            Date fechaActual = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaActual);
            calendar.add(Calendar.DAY_OF_YEAR, diasDuracion);
            Date fechaDevolucion = calendar.getTime();

            Prestamo prestamo = Prestamo.builder()
                    .estudiante(estudiante)
                    .ejemplar(ejemplar)
                    .fechaPrestamo(fechaActual)
                    .fechaDevolucion(fechaDevolucion)
                    .build();

            prestamoRepository.save(prestamo);

            ejemplar.setEsPrestado(true);
            ejemplarRepository.save(ejemplar);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(prestamo.getIdPrestamo())
                    .toUri();

            return ResponseEntity.created(location).body(prestamo);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deletePrestamoById(Long id) {
        Optional<Prestamo> optionalPrestamo = prestamoRepository.findById(id);
        if (optionalPrestamo.isPresent()) {
            prestamoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> marcarDevolucion(Long idPrestamo) {
        Optional<Prestamo> optionalPrestamo = prestamoRepository.findById(idPrestamo);
        if (optionalPrestamo.isPresent()) {
            Ejemplar ejemplar = optionalPrestamo.get().getEjemplar();
            ejemplar.setEsPrestado(false);
            ejemplarRepository.save(ejemplar);

            prestamoRepository.deleteById(idPrestamo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
