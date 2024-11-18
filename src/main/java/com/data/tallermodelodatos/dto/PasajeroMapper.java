package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Pasajero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PasajeroMapper {
    PasajeroMapper INSTANCE = Mappers.getMapper( PasajeroMapper.class );
    Pasajero pasajeroDtoToPasajero(PasajeroDto pasajeroDto);
    @Mapping(target = "idPasajero", ignore = true)
    Pasajero pasajeroDtoWithoutIdToPasajero(PasajeroDto pasajeroDto);
    PasajeroDto pasajeroToPasajeroDto(Pasajero pasajero);
    @Mapping(target = "idPasajero", ignore = true)
    PasajeroDto pasajeroToPasajeroDtoWithoutId(Pasajero pasajero);
}