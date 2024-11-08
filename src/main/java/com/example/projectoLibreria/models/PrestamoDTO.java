package com.example.projectoLibreria.models;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
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

    public PrestamoDTO(Date fechaPrestamo, Date fechaDevolucion, String nombreEstudiante, String apellidoEstudiante, String libroPrestado) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiante = apellidoEstudiante;
        this.libroPrestado = libroPrestado;
    }
}
