package com.ccg.alumno.service.webflux.dto;

import com.ccg.alumno.service.webflux.entity.Estado;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Setter
@Getter
public class AlumnoRequest {

    @NotNull
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @NotNull
    private Estado estado;

    @Min(1)
    private Integer edad;
}
