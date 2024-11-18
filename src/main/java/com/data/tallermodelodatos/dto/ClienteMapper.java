package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper( ClienteMapper.class );
    Cliente clienteDtoToCliente(ClienteDto clienteDto);
    @Mapping(target = "idCliente", ignore = true)
    Cliente clienteDtoWithoutIdToCliente(ClienteDto clienteDto);
    ClienteDto clienteToClienteDto(Cliente cliente);
    @Mapping(target = "idCliente", ignore = true)
    ClienteDto clienteToClienteDtoWithoutId(Cliente cliente);
}