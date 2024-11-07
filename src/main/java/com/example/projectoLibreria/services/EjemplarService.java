package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.models.LibroDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EjemplarService {
    ResponseEntity<Void> deleteEjemplar(Long id);

    ResponseEntity<List<LibroDTO>> obtenerLibrosDisponible();

    Ejemplar updateEstadoEjemplar(Long id, Boolean nuevoEstado);
}
