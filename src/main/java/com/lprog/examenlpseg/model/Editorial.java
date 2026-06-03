package com.lprog.examenlpseg.model;

import jakarta.persistence.*;
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
    @Size(min = 1, max = 50)
    private String nombre;
    @Column(name = "direccion")
    @Size(min = 1, max = 50)
    private String direccion;
    @Column(name = "telefono", unique = true)
    @Size(min = 9,max = 9, message = "No puede tener mas de 9 digitos")
    private String telefono;
}
