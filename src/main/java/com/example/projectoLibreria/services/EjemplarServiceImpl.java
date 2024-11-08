package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.models.EjemplarDTO;
import com.example.projectoLibreria.models.Libro;
import com.example.projectoLibreria.models.LibroDTO;
import com.example.projectoLibreria.repositories.EjemplarRepository;
import com.example.projectoLibreria.repositories.LibroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjemplarServiceImpl implements EjemplarService {

    private final EjemplarRepository ejemplarRepository;
    private final LibroRepository libroRepository;

    public EjemplarServiceImpl(EjemplarRepository ejemplarRepository, LibroRepository libroRepository) {
        this.ejemplarRepository = ejemplarRepository;
        this.libroRepository = libroRepository;
    }


    @Override
    public ResponseEntity<List<EjemplarDTO>> getEjemplares() {
        List<Ejemplar> ejemplares = ejemplarRepository.findAll();
        List<EjemplarDTO>ejemplaresDTO = ejemplares.stream()
                .map(ejemplar -> {
                    return new EjemplarDTO(ejemplar.getIdEjemplar(), ejemplar.getLibro().getTitulo(), ejemplar.getEsPrestado());
                })
                .toList();
        return ejemplares.isEmpty()? ResponseEntity.noContent().build() : ResponseEntity.ok(ejemplaresDTO);
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
                    long cantidadEjemplares = ejemplarRepository.countByLibroIdLibro(libro.getIdLibro());
                    long cantidadEjemplaresDisponibles = ejemplarRepository.countAvailableByLibroId(libro.getIdLibro());
                    return new LibroDTO(libro, cantidadEjemplares, cantidadEjemplaresDisponibles);
                })
                .toList();

        return librosDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(librosDTO);
    }

    @Override
    public ResponseEntity<EjemplarDTO> updateEstadoEjemplar(Long id, Boolean nuevoEstado) {
        Optional<Ejemplar> optionalEjemplar = ejemplarRepository.findById(id);

        if (optionalEjemplar.isPresent()) {
            Ejemplar ejemplar = optionalEjemplar.get();
            ejemplar.setEsPrestado(nuevoEstado);
            ejemplarRepository.save(ejemplar);


            EjemplarDTO ejemplarDTO = new EjemplarDTO();
            ejemplarDTO.setIdEjemplar(ejemplar.getIdEjemplar());
            ejemplarDTO.setTituloLibro(ejemplar.getLibro().getTitulo());
            ejemplarDTO.setEsPrestado(ejemplar.getEsPrestado());

            return ResponseEntity.ok(ejemplarDTO);
        }

        return ResponseEntity.notFound().build();
    }
}
