package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ClienteDto;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
     ClienteDto guardarCliente(ClienteDto clienteDto);
     Optional<ClienteDto> buscarClientePorId(Long id);
     List<ClienteDto> buscarClientes();
     List<ClienteDto> buscarClientesbyIds(List<Long> ids);
     List<ClienteDto> buscarClientePorNombre(String nombre);
     Optional<ClienteDto> actualizarCliente(Long id, ClienteDto clienteDto);
     void deleteCliente(Long id);
}