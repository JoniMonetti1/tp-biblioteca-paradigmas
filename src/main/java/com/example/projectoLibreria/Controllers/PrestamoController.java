package com.example.projectoLibreria.Controllers;

import com.example.projectoLibreria.models.Prestamo;
import com.example.projectoLibreria.services.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Prestamo>> getPrestamos() {
        return prestamoService.getPrestamos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Long id) {
        return prestamoService.getPrestamoById(id);
    }

    @PostMapping()
    public ResponseEntity<Prestamo> createPrestamo(@RequestParam Long idEstudiante, @RequestParam Long idLibro, @RequestParam int diasDuracion) {
        return prestamoService.createPrestamo(idEstudiante, idLibro, diasDuracion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamoById(@PathVariable Long id) {
        return prestamoService.deletePrestamoById(id);
    }

    @PutMapping("/{idPrestamo}")
    public ResponseEntity<Void> marcarDevolucion(@PathVariable Long idPrestamo) {
        return prestamoService.marcarDevolucion(idPrestamo);
    }
}
