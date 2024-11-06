package com.example.projectoLibreria.repositories;

import com.example.projectoLibreria.models.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
