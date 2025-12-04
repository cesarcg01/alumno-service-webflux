package com.ccg.alumno.service.webflux.service;

import com.ccg.alumno.service.webflux.dto.AlumnoRequest;
import com.ccg.alumno.service.webflux.entity.AlumnoEntity;
import com.ccg.alumno.service.webflux.entity.Estado;
import com.ccg.alumno.service.webflux.exception.AlumnoException;
import com.ccg.alumno.service.webflux.mapper.AlumnoMapper;
import com.ccg.alumno.service.webflux.repository.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AlumnoService {
    private final AlumnoRepository alumnoRepository;
    private final R2dbcEntityTemplate template;

    public Mono<Void> guardarAlumno(AlumnoRequest req){
        AlumnoEntity entity = AlumnoMapper.toEntity(req);

        return alumnoRepository.existsById(entity.getId())
                .flatMap(exist ->{
                 if(exist){
                     return Mono.error(new AlumnoException("ID ya existe"));
                 }
                 return template.insert(entity).then();
        });
    }

    public Flux<AlumnoEntity> obtenerAlumnosActivos(){
        return alumnoRepository.findByEstado(Estado.ACTIVO);
    }

}
