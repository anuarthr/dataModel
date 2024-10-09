package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Pasajero;
import org.mapstruct.Mapper;

@Mapper(uses = ReservaMapper.class)
public interface PasajeroMapper {
    PasajeroDto toDto(Pasajero pasajero);
    Pasajero toPasajero(PasajeroDto pasajeroDto);
}
