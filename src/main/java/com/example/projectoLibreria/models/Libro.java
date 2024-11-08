package com.example.projectoLibreria.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.util.List;

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

    @JsonIgnoreProperties("libro")
    @OneToMany(mappedBy = "libro")
    private List<Ejemplar> ejemplares;
}
