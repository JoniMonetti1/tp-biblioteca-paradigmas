package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.models.EjemplarDTO;
import com.example.projectoLibreria.models.LibroDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EjemplarService {

    ResponseEntity<List<EjemplarDTO>> getEjemplares();
    ResponseEntity<Void> deleteEjemplar(Long id);

    ResponseEntity<List<LibroDTO>> obtenerLibrosDisponible();

    ResponseEntity<EjemplarDTO> updateEstadoEjemplar(Long id, Boolean nuevoEstado);
}
