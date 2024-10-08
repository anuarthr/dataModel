package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Aerolinea;
import org.mapstruct.Mapper;
@Mapper(uses = VueloMapper.class)
public interface AerolineaMapper {
    AerolineaDto toDto(Aerolinea aerolinea);
    Aerolinea toAerolinea(AerolineaDto aerolineaDto);
}
