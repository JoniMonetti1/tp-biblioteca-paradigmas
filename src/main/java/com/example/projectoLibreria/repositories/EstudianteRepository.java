package com.example.projectoLibreria.repositories;

import com.example.projectoLibreria.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
