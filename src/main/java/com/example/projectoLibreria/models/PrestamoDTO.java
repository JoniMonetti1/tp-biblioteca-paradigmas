package com.example.projectoLibreria.models;

import lombok.Builder;

import java.util.Date;

@Builder
public class PrestamoDTO {
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String nombreEstudiante;
    private String apellidoEstudiante;
    private String libroPrestado;

    public PrestamoDTO(Prestamo prestamo, String libroPrestado) {
        this.fechaPrestamo = prestamo.getFechaPrestamo();
        this.fechaDevolucion = prestamo.getFechaDevolucion();
        this.nombreEstudiante = prestamo.getEstudiante().getNombre();
        this.apellidoEstudiante = prestamo.getEstudiante().getApellido();
        this.libroPrestado = libroPrestado;
    }
}
