package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Aeropuerto;
import org.mapstruct.Mapper;

@Mapper(uses = VueloMapper.class)
public interface AeropuertoMapper {
    AeropuertoDto toDto(Aeropuerto aeropuerto);
    Aeropuerto toAeropuerto(AeropuertoDto aeropuertoDto);
}
