package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Reserva;
import org.mapstruct.Mapper;

@Mapper(uses = {VueloMapper.class, ClienteMapper.class})
public interface ReservaMapper {
    ReservaDto toDto(Reserva reserva);
    Reserva toReserva(ReservaDto reservaDto);
}
