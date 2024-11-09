package com.example.projectoLibreria.services;

import com.example.projectoLibreria.models.Libro;

import java.util.List;

public interface BusquedaLibroStrategy {
    List<Libro> search(String valor);
}
