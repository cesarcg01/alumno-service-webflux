package com.ccg.alumno.service.webflux.controller;

import com.ccg.alumno.service.webflux.dto.AlumnoRequest;
import com.ccg.alumno.service.webflux.entity.AlumnoEntity;
import com.ccg.alumno.service.webflux.service.AlumnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoService alumnoService;

    @PostMapping
    public Mono<ResponseEntity<Void>> guardar(@RequestBody @Valid AlumnoRequest request){
        return alumnoService.guardarAlumno(request)
                .thenReturn(ResponseEntity.ok().build());
    }

    @GetMapping("/activos")
    public Flux<AlumnoEntity> activos(){
        return alumnoService.obtenerAlumnosActivos();
    }
}
