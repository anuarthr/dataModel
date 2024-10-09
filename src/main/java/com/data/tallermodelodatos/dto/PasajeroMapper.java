package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Pasajero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PasajeroMapper {
    PasajeroMapper INSTANCE = Mappers.getMapper( PasajeroMapper.class );
    Pasajero pasajeroDTOToPasajero(PasajeroDto pasajeroDto);
    @Mapping(target = "id", ignore = true)
    Pasajero pasajeroDTOWithoutIdToPasajero(PasajeroDto pasajeroDto);
    PasajeroDto pasajeroToPasajeroDTO(Pasajero pasajero);
    @Mapping(target = "id", ignore = true)
    PasajeroDto pasajeroToPasajeroDTOWithoutId(Pasajero pasajero);
}
