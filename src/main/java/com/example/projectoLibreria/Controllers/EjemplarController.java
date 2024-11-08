package com.example.projectoLibreria.Controllers;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.models.EjemplarDTO;
import com.example.projectoLibreria.models.LibroDTO;
import com.example.projectoLibreria.services.EjemplarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarController {
    private final EjemplarService ejemplarService;

    public EjemplarController(EjemplarService ejemplarService) {
        this.ejemplarService = ejemplarService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<EjemplarDTO>> getEjemplares() {
        return ejemplarService.getEjemplares();
    }

    @GetMapping("/disponibles")
    @CrossOrigin
    public ResponseEntity<List<LibroDTO>> obtenerLibrosDisponible() {
        return ejemplarService.obtenerLibrosDisponible();
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<EjemplarDTO> updateEstadoEjemplar(@PathVariable Long id, @RequestBody Map<String, Boolean> nuevoEstado) {
        return ejemplarService.updateEstadoEjemplar(id, nuevoEstado.get("esPrestado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjemplar(@PathVariable Long id) {
        return ejemplarService.deleteEjemplar(id);
    }
}
