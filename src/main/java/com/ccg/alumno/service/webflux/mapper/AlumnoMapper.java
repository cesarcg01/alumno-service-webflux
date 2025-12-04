package com.ccg.alumno.service.webflux.mapper;

import com.ccg.alumno.service.webflux.dto.AlumnoRequest;
import com.ccg.alumno.service.webflux.entity.AlumnoEntity;

public class AlumnoMapper {
    public static AlumnoEntity toEntity(AlumnoRequest req){
        AlumnoEntity e= new AlumnoEntity();
        e.setId(req.getId());
        e.setNombre(req.getNombre());
        e.setApellido(req.getApellido());
        e.setEstado(req.getEstado());
        e.setEdad(req.getEdad());
        return e;
    }



}
