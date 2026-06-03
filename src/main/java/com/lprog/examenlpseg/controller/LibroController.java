package com.lprog.examenlpseg.controller;

import com.lprog.examenlpseg.dto.ApiResponse;
import com.lprog.examenlpseg.model.Libro;
import com.lprog.examenlpseg.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public String mostrarPaginaLibros() {
        return "libros/listalibros";
    }

    @GetMapping("/listar")
    @ResponseBody
    public ResponseEntity<ApiResponse<Iterable<Libro>>> listar() {
        try {
            return ResponseEntity.ok(ApiResponse.ok("Listado correcto", libroService.readAll()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al obtener datos: " + e.getMessage()));
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<ApiResponse<Libro>> save(@Valid @RequestBody Libro libro, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errores.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(ApiResponse.validationError("Hay errores en el formulario", errores));
        }

        try {
            Libro guardado;
            if (libro.getId() != null) {
                guardado = libroService.update(libro);
            } else {
                guardado = libroService.create(libro);
            }
            return ResponseEntity.ok(ApiResponse.ok("Libro guardado exitosamente", guardado));

        } catch (DataIntegrityViolationException e) {
            String mensajeReal = e.getMostSpecificCause().getMessage();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error("Error BD: " + mensajeReal));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error interno: " + e.getMessage()));
        }
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> eliminar(@PathVariable Long id) {
        try {
            libroService.delete(id);
            return ResponseEntity.ok(ApiResponse.ok("Libro eliminado correctamente", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Ocurrió un error al intentar eliminar el libro."));
        }
    }
}