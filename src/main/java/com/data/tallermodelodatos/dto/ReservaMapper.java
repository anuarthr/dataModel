package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Cliente;
import com.data.tallermodelodatos.entities.Reserva;
import com.data.tallermodelodatos.entities.Vuelo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {
    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);
    Reserva reservaDtoToReserva(ReservaDto reservaDto);
    @Mapping(target = "id", ignore = true)
    Reserva reservaDtoWithoutIdToReserva(ReservaDto reservaDto);
    ReservaDto reservaToReservaDto(Reserva reserva);
    @Mapping(target = "id", ignore = true)
    ReservaDto reservaToReservaDtoWithoutId(Reserva reserva);
    Cliente clienteDtoToCliente(ClienteDto clienteDto);
    Vuelo vueloDtoToVuelo(VueloDto vueloDto);
    List<Vuelo> vueloDtosToVuelos(List<VueloDto> vueloDtos);
}
