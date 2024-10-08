package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper
public interface ClienteMapper {
    ClienteDto toDto(Cliente cliente);
    Cliente toCliente(ClienteDto clienteDto);
}
