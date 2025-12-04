package com.ccg.alumno.service.webflux.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("alumno")
public class AlumnoEntity {

    @Id
    private Long id;
    private String nombre;
    private String apellido;
    private Estado estado;
    private Integer edad;
}
