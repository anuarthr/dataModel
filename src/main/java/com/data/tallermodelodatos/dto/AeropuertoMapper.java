package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Aeropuerto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AeropuertoMapper {
    AeropuertoMapper INSTANCE = Mappers.getMapper(AeropuertoMapper.class);
    Aeropuerto aeropuertoDTOToAeropuerto(AeropuertoDto aeropuertoDto);
    @Mapping(target = "id", ignore = true)
    Aeropuerto aeropuertoDTOWithoutIdToAeropuerto(AeropuertoDto aeropuertoDto);
    AeropuertoDto aeropuertoToAeropuertoDto(Aeropuerto aeropuerto);
    @Mapping(target = "id", ignore = true)
    AeropuertoDto aeropuertoToAeropuertoDTOWithoutId(Aeropuerto aeropuerto);
}
