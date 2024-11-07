package com.example.projectoLibreria.repositories;

import com.example.projectoLibreria.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT DISTINCT l FROM Libro l JOIN Ejemplar e ON l.idLibro = e.idLibro WHERE e.esPrestado = false")
    List<Libro> findLibrosDisponibles();
}
