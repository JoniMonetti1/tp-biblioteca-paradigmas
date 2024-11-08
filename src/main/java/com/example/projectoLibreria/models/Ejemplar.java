package com.example.projectoLibreria.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonIgnoreProperties("ejemplares")
    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    @Column(name = "es_prestado")
    private Boolean esPrestado;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(mappedBy = "ejemplar")
    @JsonIgnoreProperties("ejemplar")
    private Prestamo prestamo;
}
