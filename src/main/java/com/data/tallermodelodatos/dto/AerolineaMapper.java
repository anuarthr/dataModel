package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Aerolinea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AerolineaMapper {
    AerolineaMapper INSTANCE = Mappers.getMapper(AerolineaMapper.class);
    Aerolinea aerolineaToDTO(AerolineaDto aerolineaDto);
    @Mapping(target = "id", ignore = true)
    Aerolinea aerolineaDTOWithoutIdToAerolinea(AerolineaDto aerolineaDto);
    AerolineaDto aerolineaToAerolineaDTO(Aerolinea aerolinea);
    @Mapping(target = "id", ignore = true)
    AerolineaDto aerolineaToAerolineaDTOWithoutId(Aerolinea aerolinea);
}
