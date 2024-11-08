package com.example.projectoLibreria.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EjemplarDTO {
    private Long idEjemplar;
    private String tituloLibro;
    private Boolean esPrestado;
}
