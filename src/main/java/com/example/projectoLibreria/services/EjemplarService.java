package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Ejemplar;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EjemplarService {
    ResponseEntity<List<Ejemplar>> getEjemplares();
    ResponseEntity<Ejemplar> getEjemplarById(Long id);
    ResponseEntity<Ejemplar> saveEjemplar(Ejemplar ejemplar);
    ResponseEntity<Ejemplar> updateEjemplar(Long id, Ejemplar ejemplar);
    ResponseEntity<Void> deleteEjemplar(Long id);
}
