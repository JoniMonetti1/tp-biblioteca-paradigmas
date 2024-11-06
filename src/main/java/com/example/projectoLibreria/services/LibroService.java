package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Libro;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LibroService {
    ResponseEntity<List<Libro>> getLibros();
    ResponseEntity<Libro> getLibroById(Long id);
    ResponseEntity<Libro> saveLibro(Libro libro);
    ResponseEntity<Libro> updateLibro(Long id, Libro libro);
    ResponseEntity<Void> deleteLibro(Long id);
}
