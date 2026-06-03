package com.lprog.examenlpseg.controller;

import com.lprog.examenlpseg.dto.ApiResponse;
import com.lprog.examenlpseg.model.Editorial;
import com.lprog.examenlpseg.service.EditorialService;
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
@RequestMapping("/editoriales")
public class EditorialController {

    private final EditorialService editorialService;

    @Autowired
    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping
    public String mostrarPaginaEditoriales() {
        return "editoriales/listaeditorial";
    }

    @GetMapping("/listar")
    @ResponseBody
    public ResponseEntity<ApiResponse<Iterable<Editorial>>> listar() {
        try {
            return ResponseEntity.ok(ApiResponse.ok("Listado correcto", editorialService.readAll()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error al obtener datos: " + e.getMessage()));
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<ApiResponse<Editorial>> save(@Valid @RequestBody Editorial editorial, BindingResult result) {

        if (result.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errores.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(ApiResponse.validationError("Hay errores en el formulario", errores));
        }

        try {
            Editorial guardado;
            if (editorial.getId() != null) {
                guardado = editorialService.update(editorial);
            } else {
                guardado = editorialService.create(editorial);
            }
            return ResponseEntity.ok(ApiResponse.ok("Editorial guardada exitosamente", guardado));

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error("El nombre o teléfono ya se encuentra registrado."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Ocurrió un error interno al guardar."));
        }
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> eliminar(@PathVariable Long id) {
        try {
            editorialService.delete(id);
            return ResponseEntity.ok(ApiResponse.ok("Editorial eliminada correctamente", null));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error("No se puede eliminar esta editorial porque tiene libros asociados."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Ocurrió un error al intentar eliminar."));
        }
    }
}
