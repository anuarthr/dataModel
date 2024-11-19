package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.dto.ClienteMapper;
import com.data.tallermodelodatos.entities.Cliente;
import com.data.tallermodelodatos.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteDto guardarCliente(ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.clienteDtoWithoutIdToCliente(clienteDto);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.clienteToClienteDto(savedCliente);
    }

    @Override
    public Optional<ClienteDto> buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::clienteToClienteDto);
    }

    @Override
    public List<ClienteDto> buscarClientes() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::clienteToClienteDtoWithoutId)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDto> buscarClientesbyIds(List<Long> ids) {
        return clienteRepository.findByIdClienteIn(ids).stream()
                .map(clienteMapper::clienteToClienteDtoWithoutId)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDto> buscarClientePorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre).stream()
                .map(clienteMapper::clienteToClienteDtoWithoutId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClienteDto> actualizarCliente(Long id, ClienteDto clienteDto) {
        return clienteRepository.findById(id).map(oldClient -> {
            oldClient.setNombre(clienteDto.nombre());
            oldClient.setApellido(clienteDto.apellido());
            oldClient.setEmail(clienteDto.email());
            oldClient.setDireccion(clienteDto.direccion());
            oldClient.setTelefono(clienteDto.telefono());
            Cliente updatedCliente = clienteRepository.save(oldClient);
            return clienteMapper.clienteToClienteDto(updatedCliente);
        });
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
