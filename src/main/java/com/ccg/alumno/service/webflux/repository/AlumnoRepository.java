package com.ccg.alumno.service.webflux.repository;

import com.ccg.alumno.service.webflux.entity.AlumnoEntity;
import com.ccg.alumno.service.webflux.entity.Estado;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AlumnoRepository extends ReactiveCrudRepository<AlumnoEntity, Long> {
    Flux<AlumnoEntity> findByEstado(Estado estado);
}
