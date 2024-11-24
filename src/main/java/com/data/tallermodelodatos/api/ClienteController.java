package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.dto.UserDto;
import com.data.tallermodelodatos.services.ClienteService;
import com.data.tallermodelodatos.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.data.tallermodelodatos.exception.ClientNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteController {

    private final ClienteService clienteService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    public ClienteController(ClienteService clienteService, UserService userService) {
        this.clienteService = clienteService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        logger.info("Fetching all clients");
        List<ClienteDto> clientes = clienteService.buscarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        logger.info("Fetching cliente con id: {}", id);
        return clienteService.buscarClientePorId(id)
                .map(clienteDto -> ResponseEntity.ok().body(clienteDto))
                .orElseThrow(ClientNotFoundException::new);
    }

    @PostMapping()
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) throws URISyntaxException {
        logger.info("Creando nuevo cliente {}", clienteDto);
        return createNewCliente(clienteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDto newClienteDto) {
        logger.info("Updating cliente con id: {}", id);
        Optional<ClienteDto> clienteUpdated = clienteService.actualizarCliente(id, newClienteDto);
        clienteUpdated.ifPresent(cliente -> {
            UserDto userDto = new UserDto(
                    cliente.idCliente(),
                    cliente.email(),
                    cliente.password(),
                    cliente.email(),
                    Set.of("ROLE_USER")
            );
            userService.updateUser(id, userDto);
        });
        return clienteUpdated.map(cliente -> ResponseEntity.ok(cliente))
                .orElseGet(() -> createNewCliente(newClienteDto));
    }

    private ResponseEntity<ClienteDto> createNewCliente(ClienteDto clienteDto) {
        ClienteDto newCliente = clienteService.guardarCliente(clienteDto);
        UserDto userDto = new UserDto(
                newCliente.idCliente(),
                newCliente.email(),
                newCliente.password(),
                newCliente.email(),
                Set.of("ROLE_USER")
        );
        userService.updateUser(newCliente.idCliente(), userDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCliente.idCliente())
                .toUri();
        return ResponseEntity.created(location).body(newCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        logger.info("Deleting cliente con id: {}", id);
        clienteService.deleteCliente(id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}