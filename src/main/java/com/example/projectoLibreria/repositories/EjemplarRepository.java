package com.example.projectoLibreria.repositories;

import com.example.projectoLibreria.models.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
    @Query("SELECT e FROM Ejemplar e WHERE e.idLibro = :idLibro AND e.esPrestado = false")
    Optional<Ejemplar> obtenerEjemplarDisponible(@Param("idLibro") Long idLibro);

    long countByLibroId(Long libroId);

    void deleteByLibroId(Long libroId);
}
