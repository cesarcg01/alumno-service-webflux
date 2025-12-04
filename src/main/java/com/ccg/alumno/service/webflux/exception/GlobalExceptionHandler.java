package com.ccg.alumno.service.webflux.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlumnoException.class)
    public ResponseEntity<String> handleCustom(AlumnoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
