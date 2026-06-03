package com.lprog.examenlpseg.controller;

import com.lprog.examenlpseg.dto.ApiResponse;
import com.lprog.examenlpseg.model.Libro;
import com.lprog.examenlpseg.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroController {
    @GetMapping("/libros")
    public String libros(){
        return "/libros/listalibros";
    }
    private final LibroService libroService;


    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public String categorias(Model model) {
        return "libros/listalibros";
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse> listar() {
        return ResponseEntity.ok(ApiResponse.ok("Listado correcto",libroService.readAll()));
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Libro libro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String mensaje = result.getFieldErrors().get(0).getDefaultMessage();
        }
        libroService.create(libro);
        return "redirect:/libros";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        libroService.delete(id);
        return "redirect:/libros";
    }
}
