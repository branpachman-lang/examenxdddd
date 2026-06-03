package com.lprog.examenlpseg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Column(name= "codigo", unique = true)
    @NotBlank(message = "Complete ISBN")
    @Size(min = 13, max = 13, message = "El ISBN tiene 13 digitos")
    private String isbn;

    @Column(name="aniopub")
    @NotNull(message = "Complete este campo")
    @Min(value = 1800, message = "El año debe ser mayor a 1800")
    @Max(value = 2026, message = "El año no puede ser mayor a 2026")
    private Integer anio_publicacion;

    @NotNull(message = "Debe seleccionar una editorial")
    @ManyToOne
    @JoinColumn(name= "editorial_id")
    private Editorial editorial;
}
