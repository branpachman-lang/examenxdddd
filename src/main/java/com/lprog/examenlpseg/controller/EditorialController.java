package com.lprog.examenlpseg.controller;

import com.lprog.examenlpseg.dto.ApiResponse;
import com.lprog.examenlpseg.model.Editorial;
import com.lprog.examenlpseg.service.EditorialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editoriales")
public class EditorialController {
    @GetMapping("/editoriales")
    public String editoriales(){
        return "/editoriales/listaeditorial";
    }
    private final EditorialService editorialService;

    @Autowired
    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping
    public String categorias(Model model) {
        return "editorial/listareditorial";
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse> listar() {
        return ResponseEntity.ok(ApiResponse.ok("Listado correcto",editorialService.readAll()));
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Editorial editorial, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String mensaje = result.getFieldErrors().get(0).getDefaultMessage();
        }
        editorialService.create(editorial);
        return "redirect:/editoriales";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        editorialService.delete(id);
        return "redirect:/editoriales";
    }
}
