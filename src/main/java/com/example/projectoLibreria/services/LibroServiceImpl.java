package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Libro;
import com.example.projectoLibreria.repositories.LibroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public ResponseEntity<List<Libro>> getLibros() {
        List<Libro> result = libroRepository.findAll();
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Libro> getLibroById(Long id) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        return optionalLibro.isPresent()? ResponseEntity.ok(optionalLibro.get()) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Libro> saveLibro(Libro libro) {
        Libro savedLibro = libroRepository.save(libro);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLibro.getIdLibro())
                .toUri();

        return ResponseEntity.created(location).body(savedLibro);
    }

    @Override
    public ResponseEntity<Libro> updateLibro(Long id, Libro libro) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);

        if (optionalLibro.isPresent()) {
            libro.setIdLibro(id);
            Libro updatedLibro = libroRepository.save(libro);
            return ResponseEntity.ok(updatedLibro);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteLibro(Long id) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);

        if (optionalLibro.isPresent()) {
            libroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
