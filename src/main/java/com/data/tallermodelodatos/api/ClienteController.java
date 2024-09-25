package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.entities.Cliente;
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

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @GetMapping()
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(clienteService.buscarClientes());
    }
    @GetMapping("/idCliente")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("idCliente") Long id) {
        return clienteService.buscarClientePorId(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
        return createNewCliente(cliente);
    }
    @PutMapping("/id")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteUpdated = clienteService.actualizarCliente(id, cliente);
        return clienteUpdated
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(()->{
                    return createNewCliente(cliente);
                });
    }
    private ResponseEntity<Cliente> createNewCliente(Cliente cliente) {
            Cliente newCliente = clienteService.guardarCliente(cliente);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newCliente.getIdCliente())
                    .toUri();
            return ResponseEntity.created(location).body(newCliente);
    }
    @DeleteMapping("/id")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
