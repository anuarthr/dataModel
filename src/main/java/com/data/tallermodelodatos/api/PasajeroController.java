package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.entities.Pasajero;
import com.data.tallermodelodatos.services.PasajeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pasajero")
public class PasajeroController {

    private final PasajeroService pasajeroService;


    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @GetMapping()
    public ResponseEntity<List<Pasajero>> getAllPasajeros() {
        return ResponseEntity.ok(pasajeroService.buscarPasajeros());
    }

    @GetMapping("/id")
    public ResponseEntity<Pasajero> getPasajeroById(@PathVariable Long id) {
        return pasajeroService.buscarPasajeroPorId(id)
                .map(pasajero -> ResponseEntity.ok().body(pasajero))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pasajero> createPasajero(@RequestBody Pasajero pasajero) throws URISyntaxException {
        return crearNuevoPasajero(pasajero);
    }

    @PutMapping("/id")
    public ResponseEntity<Pasajero> actualizarPasajero(@PathVariable Long id, @RequestBody Pasajero nuevoPasajero) {
        Optional<Pasajero> pasajeroUpdate = pasajeroService.actualizarPasajero(id, nuevoPasajero);
        return pasajeroUpdate.map(pasajero -> ResponseEntity.ok(pasajero))
                .orElseGet(() -> {
                    return crearNuevoPasajero(nuevoPasajero);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletePasajero(@PathVariable Long id) {
        pasajeroService.eliminarPasajero(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Pasajero> crearNuevoPasajero(Pasajero pasajero) {
        Pasajero nuevoPasajero = pasajeroService.guardarPasajero(pasajero);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoPasajero.getIdPasajero())
                .toUri();
        return ResponseEntity.created(location).body(nuevoPasajero);
    }
}
