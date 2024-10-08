package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.entities.Cliente;
import com.data.tallermodelodatos.dto.ClienteMapper;
import com.data.tallermodelodatos.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<Cliente> clientes = clienteService.buscarClientes();
        List<ClienteDto> clienteDtos = clientes.stream()
                .map(clienteMapper::toDto)
                .toList();
        return ResponseEntity.ok(clienteDtos);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable("idCliente") Long id) {
        return clienteService.buscarClientePorId(id)
                .map(cliente -> ResponseEntity.ok(clienteMapper.toDto(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toCliente(clienteDto);
        Cliente newCliente = clienteService.guardarCliente(cliente);
        ClienteDto newClienteDto = clienteMapper.toDto(newCliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newClienteDto.idCliente())
                .toUri();
        return ResponseEntity.created(location).body(newClienteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toCliente(clienteDto);
        Optional<Cliente> clienteUpdated = clienteService.actualizarCliente(id, cliente);
        return clienteUpdated
                .map(c -> ResponseEntity.ok(clienteMapper.toDto(c)))
                .orElseGet(() -> createCliente(clienteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
