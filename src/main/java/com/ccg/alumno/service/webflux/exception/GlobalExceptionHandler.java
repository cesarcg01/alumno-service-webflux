package com.ccg.alumno.service.webflux.exception;

import com.ccg.alumno.service.webflux.dto.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ApiResponse<Object>> handleValidationErrors(WebExchangeBindException ex) {

        String mensaje = ex.getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Error de validación");

        return Mono.just(ApiResponse.error(mensaje));
    }

    @ExceptionHandler(AlumnoException.class)
    public Mono<ApiResponse<Object>> handleAlumnoException(AlumnoException ex) {
        return Mono.just(ApiResponse.error(ex.getMessage()));
    }
}
