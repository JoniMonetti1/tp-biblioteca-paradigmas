package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.repositories.EjemplarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class EjemplarServiceImpl implements EjemplarService{

    private final EjemplarRepository ejemplarRepository;

    public EjemplarServiceImpl(EjemplarRepository ejemplarRepository) {
        this.ejemplarRepository = ejemplarRepository;
    }

    @Override
    public ResponseEntity<List<Ejemplar>> getEjemplares() {
        List<Ejemplar> result = ejemplarRepository.findAll();
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Ejemplar> getEjemplarById(Long id) {
        Optional<Ejemplar> ejemplarOptional = ejemplarRepository.findById(id);
        return ejemplarOptional.isPresent() ? ResponseEntity.ok(ejemplarOptional.get()) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Ejemplar> saveEjemplar(Ejemplar ejemplar) {
        Ejemplar savedEjemplar = ejemplarRepository.save(ejemplar);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEjemplar.getIdEjemplar())
                .toUri();

        return ResponseEntity.created(location).body(savedEjemplar);
    }

    @Override
    public ResponseEntity<Ejemplar> updateEjemplar(Long id, Ejemplar ejemplar) {
        Optional<Ejemplar> optionalEjemplar = ejemplarRepository.findById(id);

        if (optionalEjemplar.isPresent()) {
            ejemplar.setIdEjemplar(id);
            Ejemplar updatedEjemplar = ejemplarRepository.save(ejemplar);
            return ResponseEntity.ok(updatedEjemplar);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteEjemplar(Long id) {
        Optional<Ejemplar> optionalEjemplar = ejemplarRepository.findById(id);

        if (optionalEjemplar.isPresent()) {
            ejemplarRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
