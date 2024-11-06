package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Estudiante;
import com.example.projectoLibreria.repositories.EstudianteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class EstudianteServiceImpl implements EstudianteService{

    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
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
    public ResponseEntity<Estudiante> updateEstudiante(Long id, Estudiante estudiante) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);

        if (optionalEstudiante.isPresent()) {
            estudiante.setIdEstudiante(id);
            Estudiante updatedEstudiante = estudianteRepository.save(estudiante);
            return ResponseEntity.ok(updatedEstudiante);
        }
        return ResponseEntity.notFound().build();
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
}
