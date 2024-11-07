package com.example.projectoLibreria.models;

public class LibroDTO {
    private Long idLibro;
    private String titulo;
    private String autor;
    private String genero;
    private Integer anioPublicacion;
    private long cantidadDeEjemplares;

    public LibroDTO(Libro libro, long cantidadDeEjemplares) {
        this.idLibro = libro.getIdLibro();
        this.titulo = libro.getTitulo();
        this.autor = libro.getAutor();
        this.genero = libro.getGenero();
        this.anioPublicacion = libro.getAnioPublicacion();
        this.cantidadDeEjemplares = cantidadDeEjemplares;
    }
}
