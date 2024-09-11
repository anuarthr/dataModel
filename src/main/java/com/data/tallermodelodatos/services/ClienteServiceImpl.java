package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Cliente;
import com.data.tallermodelodatos.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    /*public void setClienteRepository(ClienteRepository clienteRepository) {
        setClienteRepository(clienteRepository);
    }*/

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> buscarClientesbyIds(List<Long> ids) {
        return clienteRepository.findByIdIn(ids);
    }

    @Override
    public List<Cliente> buscarClientePorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> actualizarCliente(Long id, Cliente cliente) {
        return clienteRepository.findById(id).map(oldClient -> {
            oldClient.setNombre(cliente.getNombre());
            oldClient.setApellido(cliente.getApellido());
            oldClient.setEmail(cliente.getEmail());
            oldClient.setDireccion(cliente.getDireccion());
            oldClient.setTelefono(cliente.getTelefono());
            return clienteRepository.save(oldClient);
        });
    }
}
