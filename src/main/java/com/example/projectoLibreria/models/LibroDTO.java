package com.example.projectoLibreria.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LibroDTO {
    private Long idLibro;
    private String titulo;
    private String autor;
    private String genero;
    private Integer anioPublicacion;
    private long cantidadDeEjemplares;
    private long cantidadDeEjemplaresDisponibles;

    public LibroDTO(Libro libro, long cantidadDeEjemplares, long cantidadDeEjemplaresDisponibles) {
        this.idLibro = libro.getIdLibro();
        this.titulo = libro.getTitulo();
        this.autor = libro.getAutor();
        this.genero = libro.getGenero();
        this.anioPublicacion = libro.getAnioPublicacion();
        this.cantidadDeEjemplares = cantidadDeEjemplares;
        this.cantidadDeEjemplaresDisponibles = cantidadDeEjemplaresDisponibles;
    }
}
