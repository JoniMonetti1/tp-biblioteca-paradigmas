package com.example.projectoLibreria.repositories;

import com.example.projectoLibreria.models.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
}
