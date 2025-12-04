package com.ccg.alumno.service.webflux.service;

import com.ccg.alumno.service.webflux.dto.AlumnoRequest;
import com.ccg.alumno.service.webflux.entity.AlumnoEntity;
import com.ccg.alumno.service.webflux.entity.Estado;
import com.ccg.alumno.service.webflux.exception.AlumnoException;
import com.ccg.alumno.service.webflux.repository.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {
    @Mock
    private AlumnoRepository alumnoRepository;

    @Mock
    private R2dbcEntityTemplate template;

    @InjectMocks
    private AlumnoService alumnoService;

    @Test
    void guardarAlumno_whenIdExists_shouldError() {
        AlumnoRequest req = new AlumnoRequest();
        req.setId(1L);
        Mockito.when(alumnoRepository.existsById(1L)).thenReturn(Mono.just(true));
        StepVerifier.create(alumnoService.guardarAlumno(req))
                .expectErrorMatches(throwable ->
                        throwable instanceof AlumnoException &&
                                throwable.getMessage().contains("ID ya existe"))
                .verify();
        Mockito.verify(alumnoRepository).existsById(1L);
    }

    @Test
    void guardarAlumno_whenIdNotExists_shouldInsert(){
        AlumnoRequest req = new AlumnoRequest();
        req.setId(2L);
        req.setNombre("Juan");
        req.setApellido("Perez");
        req.setEstado(Estado.ACTIVO);
        req.setEdad(20);

        AlumnoEntity entity = new AlumnoEntity();
        entity.setId(2L);
        entity.setNombre("Juan");
        entity.setApellido("Perez");
        entity.setEstado(Estado.ACTIVO);
        entity.setEdad(20);

        Mockito.when(alumnoRepository.existsById(2L))
                .thenReturn(Mono.just(false));

        Mockito.when(template.insert(Mockito.any(AlumnoEntity.class)))
                .thenReturn(Mono.just(entity));

        StepVerifier.create(alumnoService.guardarAlumno(req))
                .verifyComplete();

        Mockito.verify(alumnoRepository).existsById(2L);
        Mockito.verify(template).insert(Mockito.any(AlumnoEntity.class));
    }

}
