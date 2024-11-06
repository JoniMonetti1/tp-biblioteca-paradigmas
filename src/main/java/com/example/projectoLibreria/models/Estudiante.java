package com.example.projectoLibreria.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    @Column(name = "id_estudiante")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstudiante;

    private String nombre;
    private String apellido;
}
