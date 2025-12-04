package com.ccg.alumno.service.webflux.repository;

import com.ccg.alumno.service.webflux.entity.AlumnoEntity;
import com.ccg.alumno.service.webflux.entity.Estado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.test.StepVerifier;

@DataR2dbcTest
public class AlumnoRepositoryTest {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private R2dbcEntityTemplate template;

    @Test
    void findByEstado_shouldReturnActiveAlumnos() {

        AlumnoEntity alumno = new AlumnoEntity();
        alumno.setId(100L);
        alumno.setNombre("Pedro");
        alumno.setApellido("Gomez");
        alumno.setEdad(30);
        alumno.setEstado(Estado.ACTIVO);

        StepVerifier.create(template.insert(AlumnoEntity.class).using(alumno))
                .expectNextCount(1)
                .verifyComplete();

        StepVerifier.create(alumnoRepository.findByEstado(Estado.ACTIVO))
                .expectNextMatches(a ->
                        a.getId().equals(100L) &&
                                a.getNombre().equals("Pedro") &&
                                a.getEstado() == Estado.ACTIVO
                )
                .verifyComplete();
    }
}
