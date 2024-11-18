package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Reserva;
import com.data.tallermodelodatos.entities.Vuelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VueloMapper {
    VueloMapper INSTANCE = Mappers.getMapper(VueloMapper.class);
    Vuelo vueloDtoToVuelo(VueloDto vueloDto);
    @Mapping(target = "idVuelo", ignore = true)
    Vuelo vueloDtoWithoutIdToVuelo(VueloDto vueloDto);
    VueloDto vueloToVueloDto(Vuelo vuelo);
    @Mapping(target = "idVuelo", ignore = true)
    VueloDto vueloToVueloDtoWithoutId(Vuelo vuelo);
}
