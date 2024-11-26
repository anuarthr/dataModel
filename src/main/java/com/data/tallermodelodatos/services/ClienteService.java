package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.entities.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
     ClienteDto guardarCliente(ClienteDto clienteDto);
     Optional<ClienteDto> buscarClientePorId(Long id);
     Optional<Cliente> buscarClienteEntityPorId(Long id);
     List<ClienteDto> buscarClientes();
     List<ClienteDto> buscarClientesbyIds(List<Long> ids);
     List<ClienteDto> buscarClientePorNombre(String nombre);
     Optional<ClienteDto> actualizarCliente(Long id, ClienteDto clienteDto);
     void deleteCliente(Long id);
}