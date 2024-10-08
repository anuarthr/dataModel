package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Vuelo;
import org.mapstruct.Mapper;

@Mapper(uses = {ReservaMapper.class})
public interface VueloMapper {
    VueloDto toDto(Vuelo vuelo);
    Vuelo toVuelo(VueloDto vueloDto);
}


