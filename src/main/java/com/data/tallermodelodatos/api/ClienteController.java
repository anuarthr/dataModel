package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.data.tallermodelodatos.exception.ClientNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> clientes = clienteService.buscarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id)
                .map(clienteDto -> ResponseEntity.ok().body(clienteDto))
                .orElseThrow(ClientNotFoundException::new);
    }

    @PostMapping()
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) throws URISyntaxException {
        return createNewCliente(clienteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDto newClienteDto) {
        Optional<ClienteDto> clienteUpdated = clienteService.actualizarCliente(id, newClienteDto);
        return clienteUpdated.map(cliente -> ResponseEntity.ok(cliente))
                .orElseGet(() -> createNewCliente(newClienteDto));
    }

    private ResponseEntity<ClienteDto> createNewCliente(ClienteDto clienteDto) {
        ClienteDto newCliente = clienteService.guardarCliente(clienteDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCliente.idCliente())
                .toUri();
        return ResponseEntity.created(location).body(newCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}