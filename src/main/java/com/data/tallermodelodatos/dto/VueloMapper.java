package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Vuelo;
import com.data.tallermodelodatos.entities.Aerolinea;
import com.data.tallermodelodatos.entities.Aeropuerto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VueloMapper {
    VueloMapper INSTANCE = Mappers.getMapper(VueloMapper.class);

    @Mapping(source = "aerolinea.idAerolinea", target = "aerolineaId")
    @Mapping(source = "aeropuerto.idAeropuerto", target = "aeropuertoId")
    VueloDto vueloToVueloDto(Vuelo vuelo);

    @Mapping(source = "aerolineaId", target = "aerolinea.idAerolinea")
    @Mapping(source = "aeropuertoId", target = "aeropuerto.idAeropuerto")
    Vuelo vueloDtoToVuelo(VueloDto vueloDto);

    Aerolinea aerolineaDtoToAerolinea(Long aerolineaId);
    Aeropuerto aeropuertoDtoToAeropuerto(Long aeropuertoId);
}