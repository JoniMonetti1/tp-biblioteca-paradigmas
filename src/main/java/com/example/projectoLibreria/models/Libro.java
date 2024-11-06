package com.example.projectoLibreria.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "libros")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    @Column(name = "id_libro")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;
    private String titulo;
    private String autor;
    private String genero;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;
}
