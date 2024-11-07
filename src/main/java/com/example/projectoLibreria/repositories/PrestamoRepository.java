package com.example.projectoLibreria.repositories;

import com.example.projectoLibreria.models.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    @Query("SELECT p FROM Prestamo p WHERE p.estudiante.idEstudiante = :idEstudiante")
    List<Prestamo> findByEstudianteId(@Param("idEstudiante") Long idEstudiante);
}
