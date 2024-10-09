package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
     Cliente guardarCliente(Cliente cliente);
     Optional<Cliente> buscarClientePorId(Long id);
     List<Cliente> buscarClientes();
     List<Cliente> buscarClientesbyIds(List<Long> ids);
     List<Cliente> buscarClientePorNombre(String nombre);
     Cliente actualizarCliente(Cliente cliente);
     Optional<Cliente> actualizarCliente(Long id, Cliente cliente);
     void deleteCliente(Long id);
}
