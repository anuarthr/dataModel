package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Aerolinea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AerolineaMapper {
    AerolineaMapper INSTANCE = Mappers.getMapper(AerolineaMapper.class);
    Aerolinea aerolineaDtoToAerolinea(AerolineaDto aerolineaDto);
    @Mapping(target = "idAerolinea", ignore = true)
    Aerolinea aerolineaDtoWithoutIdToAerolinea(AerolineaDto aerolineaDto);
    AerolineaDto aerolineaToAerolineaDto(Aerolinea aerolinea);
    @Mapping(target = "idAerolinea", ignore = true)
    AerolineaDto aerolineaToAerolineaDtoWithoutId(Aerolinea aerolinea);
}
