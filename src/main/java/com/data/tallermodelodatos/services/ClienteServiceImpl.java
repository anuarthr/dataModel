package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.entities.Cliente;
import com.data.tallermodelodatos.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDto> buscarClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDto> buscarClientesbyIds(List<Long> ids) {
        return List.of();
    }

    @Override
    public List<ClienteDto> buscarClientePorNombre(String nombre) {
        return List.of();
    }

    @Override
    public Optional<ClienteDto> buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    public ClienteDto guardarCliente(ClienteDto clienteDto) {
        Cliente cliente = convertToEntity(clienteDto);
        Cliente savedCliente = clienteRepository.save(cliente);
        return convertToDto(savedCliente);
    }

    @Override
    public Optional<ClienteDto> actualizarCliente(Long id, ClienteDto clienteDto) {
        return clienteRepository.findById(id)
                .map(existingCliente -> {
                    existingCliente.setNombre(clienteDto.nombre());
                    existingCliente.setApellido(clienteDto.apellido());
                    existingCliente.setDireccion(clienteDto.direccion());
                    existingCliente.setTelefono(clienteDto.telefono());
                    existingCliente.setEmail(clienteDto.email());
                    existingCliente.setPassword(clienteDto.password());
                    Cliente updatedCliente = clienteRepository.save(existingCliente);
                    return convertToDto(updatedCliente);
                });
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDto convertToDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getPassword()
        );
    }

    private Cliente convertToEntity(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(clienteDto.idCliente());
        cliente.setNombre(clienteDto.nombre());
        cliente.setApellido(clienteDto.apellido());
        cliente.setDireccion(clienteDto.direccion());
        cliente.setTelefono(clienteDto.telefono());
        cliente.setEmail(clienteDto.email());
        cliente.setPassword(clienteDto.password());
        return cliente;
    }
}