package com.ccg.alumno.service.webflux.controller;

import com.ccg.alumno.service.webflux.dto.AlumnoRequest;
import com.ccg.alumno.service.webflux.entity.AlumnoEntity;
import com.ccg.alumno.service.webflux.entity.Estado;
import com.ccg.alumno.service.webflux.service.AlumnoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = AlumnoController.class)
@Import(TestConfig.class)
public class AlumnoControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AlumnoService alumnoService;

    @Test
    void shouldSaveAlumno() {
        AlumnoRequest req = new AlumnoRequest();
        req.setId(1L);
        req.setNombre("Cesar");
        req.setApellido("Cochachin");
        req.setEstado(Estado.ACTIVO);
        req.setEdad(20);

        Mockito.when(alumnoService.guardarAlumno(Mockito.any()))
                .thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/api/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

    @Test
    void shouldGetActivos() {
        AlumnoEntity e = new AlumnoEntity();
        e.setId(10L);
        e.setNombre("Luis");
        e.setApellido("Diaz");
        e.setEdad(22);
        e.setEstado(Estado.ACTIVO);

        Mockito.when(alumnoService.obtenerAlumnosActivos())
                .thenReturn(Flux.just(e));

        webTestClient.get()
                .uri("/api/alumnos/activos")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(10)
                .jsonPath("$[0].nombre").isEqualTo("Luis")
                .jsonPath("$[0].estado").isEqualTo("ACTIVO");
    }
}
