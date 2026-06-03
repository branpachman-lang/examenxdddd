package com.lprog.examenlpseg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "editoriales")
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", unique = true)
    @NotBlank(message = "El nombre de la editorial es obligatorio")
    @Size(min = 1, max = 50, message = "El nombre no puede exceder los 50 caracteres")
    private String nombre;

    @Column(name = "direccion")
    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 1, max = 50, message = "La dirección no puede exceder los 50 caracteres")
    private String direccion;

    @Column(name = "telefono", unique = true)
    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 9, max = 9, message = "El teléfono debe tener exactamente 9 dígitos")
    private String telefono;
}
