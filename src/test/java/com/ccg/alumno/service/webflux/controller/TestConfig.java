package com.ccg.alumno.service.webflux.controller;

import com.ccg.alumno.service.webflux.service.AlumnoService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public AlumnoService alumnoService() {
        return Mockito.mock(AlumnoService.class);
    }
}
