package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Libro;
import com.example.projectoLibreria.repositories.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarPorGeneroStrategy implements BusquedaLibroStrategy{

    private final LibroRepository libroRepository;

    public BuscarPorGeneroStrategy(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> search(String valor) {
        return libroRepository.findByGeneroContainingIgnoreCase(valor);
    }
}
