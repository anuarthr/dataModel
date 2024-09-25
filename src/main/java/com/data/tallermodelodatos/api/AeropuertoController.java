package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.entities.Aeropuerto;
import com.data.tallermodelodatos.services.AeropuertoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aeropuerto")
public class AeropuertoController {

    private final AeropuertoService aeropuertoService;


    public AeropuertoController(AeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }

    @GetMapping()
    public ResponseEntity<List<Aeropuerto>> getAllAeropuerto() {
        return ResponseEntity.ok(aeropuertoService.buscarAeropuertos());
    }

    @GetMapping("/id")
    public ResponseEntity<Aeropuerto> getAeropuertoById(@PathVariable Long id) {
        return aeropuertoService.buscarAeropuertoPorId(id)
                .map(aeropuerto -> ResponseEntity.ok().body(aeropuerto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aeropuerto> createAeropuerto(@RequestBody Aeropuerto aeropuerto) throws URISyntaxException {
        return crearNuevoAeropuerto(aeropuerto);
    }

    @PutMapping("/id")
    public ResponseEntity<Aeropuerto> actualizarAeropuerto(@PathVariable Long id, @RequestBody Aeropuerto nuevoAeropuerto) throws URISyntaxException {
        Optional<Aeropuerto> aeropuertoUpdate = aeropuertoService.actualizarAeropuerto(id, nuevoAeropuerto);
        return aeropuertoUpdate.map(aeropuerto -> ResponseEntity.ok(aeropuerto))
                .orElseGet(() -> {
                    return crearNuevoAeropuerto(nuevoAeropuerto);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteAeropuerto(@PathVariable Long id) {
        aeropuertoService.eliminarAeropuerto(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Aeropuerto> crearNuevoAeropuerto(Aeropuerto aeropuerto) {
        Aeropuerto nuevoAeropuerto = aeropuertoService.guardarAeropuerto(aeropuerto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoAeropuerto.getIdAeropuerto())
                .toUri();
        return ResponseEntity.created(location).body(nuevoAeropuerto);
    }
}
