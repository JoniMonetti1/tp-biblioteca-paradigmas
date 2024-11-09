package com.example.projectoLibreria.repositories;

import com.example.projectoLibreria.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT DISTINCT l FROM Libro l WHERE EXISTS (SELECT e FROM Ejemplar e WHERE e.libro = l AND e.esPrestado = false)")
    List<Libro> findLibrosDisponibles();

    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByAutorContainingIgnoreCase(String autor);
    List<Libro> findByGeneroContainingIgnoreCase(String genero);
}
