package com.example.projectoLibreria.repositories;

import com.example.projectoLibreria.models.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
    @Query("SELECT e FROM Ejemplar e WHERE e.libro.idLibro = :idLibro AND e.esPrestado = false ORDER BY e.idEjemplar ASC LIMIT 1")
    Optional<Ejemplar> obtenerEjemplarDisponible(@Param("idLibro") Long idLibro);

    @Query("SELECT COUNT(e) FROM Ejemplar e WHERE e.libro.idLibro = :idLibro AND e.esPrestado = false")
    long countAvailableByLibroId(@Param("idLibro") Long idLibro);

    long countByLibroIdLibro(Long libroId);

    void deleteByLibroIdLibro(Long libroId);
}
