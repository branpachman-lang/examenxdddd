package com.lprog.examenlpseg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "título")
    @NotBlank(message = "Complete este campo")
    @Size( min = 1, max = 50)
    private String titulo;
    @Column(name= "codigo")
    @NotBlank(message = "Complete ISBN")
    @Size(min = 13, max = 13, message = "El ISBN tiene 13 digitos")
    private String isbn;
    @Column(name="añopub")
    @NotBlank(message = "Complete este campo")
    @Size(min = 1800, max = 2026, message = "La fecha debe estar entre 1800 y 2026")
    private String anio_publicacion;

    @NotNull
    @ManyToOne
    @JoinColumn(name= "editorial_id")
    private Editorial editorial;
}
