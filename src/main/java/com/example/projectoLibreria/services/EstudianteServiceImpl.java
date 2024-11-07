package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Estudiante;
import com.example.projectoLibreria.models.Libro;
import com.example.projectoLibreria.models.Prestamo;
import com.example.projectoLibreria.models.PrestamoDTO;
import com.example.projectoLibreria.repositories.EstudianteRepository;
import com.example.projectoLibreria.repositories.LibroRepository;
import com.example.projectoLibreria.repositories.PrestamoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class EstudianteServiceImpl implements EstudianteService{

    private final EstudianteRepository estudianteRepository;
    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository, PrestamoRepository prestamoRepository, LibroRepository libroRepository) {
        this.estudianteRepository = estudianteRepository;
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    public ResponseEntity<List<Estudiante>> getEstudiantes() {
        List<Estudiante> result = estudianteRepository.findAll();
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Estudiante> getEstudianteById(Long id) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);
        return optionalEstudiante.isPresent()? ResponseEntity.ok(optionalEstudiante.get()) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Estudiante> createEstudiante(Estudiante estudiante) {
        Estudiante savedEstudiante = estudianteRepository.save(estudiante);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEstudiante.getIdEstudiante())
                .toUri();

        return ResponseEntity.created(location).body(savedEstudiante);
    }

    @Override
    public ResponseEntity<Void> deleteEstudiante(Long id) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);

        if (optionalEstudiante.isPresent()) {
            estudianteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<PrestamoDTO>> obtenerPrestamosPorEstudiante(Long idEstudiante) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(idEstudiante);
        if (optionalEstudiante.isPresent()) {
            List<Prestamo> prestamos = prestamoRepository.findByEstudianteId(idEstudiante);

            if (prestamos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<PrestamoDTO> listaDePrestamosDTO = prestamos.stream()
                    .map(prestamo -> {
                        String tituloLibro = libroRepository.findById(prestamo.getEjemplar().getIdLibro())
                                .map(libro -> {
                                    return libro.getTitulo();
                                })
                                .orElse("Libro no encontrado");
                        return new PrestamoDTO(prestamo, tituloLibro);
                    }).toList();

            return ResponseEntity.ok(listaDePrestamosDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
