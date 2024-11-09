package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.models.Libro;
import com.example.projectoLibreria.models.LibroDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LibroService {
    ResponseEntity<List<LibroDTO>> getLibros();
    ResponseEntity<LibroDTO> getLibroById(Long id);
    ResponseEntity<Libro> saveLibro(Libro libro);
    ResponseEntity<Libro> updateLibro(Long id, Libro libro);
    ResponseEntity<Void> deleteLibro(Long id);
    ResponseEntity<Ejemplar> agregarEjemplar(Long idLibro);
    ResponseEntity<List<Libro>> buscarLibros(String criterio, String valor);
}
