package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.models.Libro;
import com.example.projectoLibreria.models.LibroDTO;
import com.example.projectoLibreria.repositories.EjemplarRepository;
import com.example.projectoLibreria.repositories.LibroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class EjemplarServiceImpl implements EjemplarService {

    private final EjemplarRepository ejemplarRepository;
    private final LibroRepository libroRepository;

    public EjemplarServiceImpl(EjemplarRepository ejemplarRepository, LibroRepository libroRepository) {
        this.ejemplarRepository = ejemplarRepository;
        this.libroRepository = libroRepository;
    }


    @Override
    public ResponseEntity<Void> deleteEjemplar(Long id) {
        Optional<Ejemplar> optionalEjemplar = ejemplarRepository.findById(id);

        if (optionalEjemplar.isPresent()) {
            ejemplarRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<LibroDTO>> obtenerLibrosDisponible() {
        List<Libro> librosDisponibles = libroRepository.findLibrosDisponibles();

        List<LibroDTO> librosDTO = librosDisponibles.stream()
                .map(libro -> {
                    long cantidadEjemplares = ejemplarRepository.countByLibroId(libro.getIdLibro());
                    return new LibroDTO(libro, cantidadEjemplares);
                })
                .toList();

        return librosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(librosDTO);
    }

    @Override
    public Ejemplar updateEstadoEjemplar(Long id, Boolean nuevoEstado) {
        Optional<Ejemplar> optionalEjemplar = ejemplarRepository.findById(id);

        if (optionalEjemplar.isPresent()) {
            optionalEjemplar.get().setEsPrestado(nuevoEstado);
            ejemplarRepository.save(optionalEjemplar.get());
            return optionalEjemplar.get();
        }

        return null;
    }
}
