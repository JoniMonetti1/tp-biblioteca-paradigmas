package com.example.projectoLibreria.Controllers;

import com.example.projectoLibreria.models.Estudiante;
import com.example.projectoLibreria.models.PrestamoDTO;
import com.example.projectoLibreria.services.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Estudiante>> getEstudiantes() {
        return estudianteService.getEstudiantes();
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        return estudianteService.getEstudianteById(id);
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Estudiante> createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.createEstudiante(estudiante);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
        return estudianteService.deleteEstudiante(id);
    }

    @GetMapping("/{idEstudiante}/prestamos")
    @CrossOrigin
    public ResponseEntity<List<PrestamoDTO>> obtenerPrestamosPorEstudiante(@PathVariable Long idEstudiante) {
        return estudianteService.obtenerPrestamosPorEstudiante(idEstudiante);
    }


}
