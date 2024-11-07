package com.example.projectoLibreria.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ejemplares")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ejemplar {
    @Column(name = "id_ejemplar")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEjemplar;

    @Column(name = "id_libro")
    private Long idLibro;

    @Column(name = "es_prestado")
    private Boolean esPrestado;
}
