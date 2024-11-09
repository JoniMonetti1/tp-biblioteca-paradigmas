package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.models.Libro;
import com.example.projectoLibreria.models.LibroDTO;
import com.example.projectoLibreria.repositories.EjemplarRepository;
import com.example.projectoLibreria.repositories.LibroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final EjemplarRepository ejemplarRepository;
    private final Map<String, BusquedaLibroStrategy> strategies = new HashMap<>();

    public LibroServiceImpl(LibroRepository libroRepository, EjemplarRepository ejemplarRepository,
                            BuscarPorAutorStrategy porAutor,
                            BuscarPorGeneroStrategy porGenero,
                            BuscarPorTituloStrategy porTitulo) {
        strategies.put("titulo", porTitulo);
        strategies.put("autor", porAutor);
        strategies.put("genero", porGenero);
        this.libroRepository = libroRepository;
        this.ejemplarRepository = ejemplarRepository;
    }

    @Override
    public ResponseEntity<List<LibroDTO>> getLibros() {
        List<Libro> result = libroRepository.findAll();
        List<LibroDTO> libroDTOS = result.stream()
                .map(libro -> {
                    long cantidadEjemplares = ejemplarRepository.countByLibroIdLibro(libro.getIdLibro());
                    long cantidadEjemplaresDisponibles = ejemplarRepository.countAvailableByLibroId(libro.getIdLibro());
                    return new LibroDTO(libro, cantidadEjemplares, cantidadEjemplaresDisponibles);
                })
                .toList();
        return libroDTOS.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(libroDTOS);
    }

    @Override
    public ResponseEntity<LibroDTO> getLibroById(Long id) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        if (optionalLibro.isPresent()) {
            Libro libro = optionalLibro.get();
            long cantidadEjemplares = ejemplarRepository.countByLibroIdLibro(libro.getIdLibro());
            long cantidadEjemplaresDisponibles = ejemplarRepository.countAvailableByLibroId(libro.getIdLibro());
            return ResponseEntity.ok(new LibroDTO(libro, cantidadEjemplares, cantidadEjemplaresDisponibles));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Libro> saveLibro(Libro libro) {
        Libro savedLibro = libroRepository.save(libro);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLibro.getIdLibro())
                .toUri();

        return ResponseEntity.created(location).body(savedLibro);
    }

    @Override
    public ResponseEntity<Libro> updateLibro(Long id, Libro libro) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);

        if (optionalLibro.isPresent()) {
            libro.setIdLibro(id);
            Libro updatedLibro = libroRepository.save(libro);
            return ResponseEntity.ok(updatedLibro);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteLibro(Long id) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);

        if (optionalLibro.isPresent()) {
            ejemplarRepository.deleteByLibroIdLibro(id);
            libroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Ejemplar> agregarEjemplar(Long idLibro) {
        Optional<Libro> optionalLibro = libroRepository.findById(idLibro);
        if (optionalLibro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Libro libro = optionalLibro.get();

        Ejemplar nuevoEjemplar = Ejemplar.builder()
                .libro(libro)
                .esPrestado(false)
                .build();
        Ejemplar savedEjemplar = ejemplarRepository.save(nuevoEjemplar);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEjemplar.getIdEjemplar())
                .toUri();

        return ResponseEntity.created(location).body(savedEjemplar);
    }

    @Override
    public ResponseEntity<List<Libro>> buscarLibros(String criterio, String valor) {
        BusquedaLibroStrategy strategy = strategies.get(criterio.toLowerCase());
        if (strategy == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(strategy.search(valor));
    }
}
