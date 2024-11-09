package com.example.projectoLibreria.Controllers;

import com.example.projectoLibreria.models.Ejemplar;
import com.example.projectoLibreria.models.Libro;
import com.example.projectoLibreria.models.LibroDTO;
import com.example.projectoLibreria.services.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<LibroDTO>> getLibros() {
        return libroService.getLibros();
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<LibroDTO> getLibroById(@PathVariable Long id) {
        return libroService.getLibroById(id);
    }

    @GetMapping("/buscar")
    @CrossOrigin
    public ResponseEntity<List<Libro>> buscarLibros(@RequestParam String criterio, @RequestParam String valor) {
        return libroService.buscarLibros(criterio, valor);
    }


    @PostMapping
    @CrossOrigin
    public ResponseEntity<Libro> saveLibro(@RequestBody Libro libro) {
        return libroService.saveLibro(libro);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.updateLibro(id, libro);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        return libroService.deleteLibro(id);
    }

    @PostMapping("/{idLibro}/ejemplar")
    @CrossOrigin
    public ResponseEntity<Ejemplar> agregarEjemplar(@PathVariable Long idLibro) {
        return libroService.agregarEjemplar(idLibro);
    }
}
