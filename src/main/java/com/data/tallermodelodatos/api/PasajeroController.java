package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.PasajeroDto;
import com.data.tallermodelodatos.services.PasajeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pasajeros")

public class PasajeroController {

    private final PasajeroService pasajeroService;


    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @GetMapping()
    public ResponseEntity<List<PasajeroDto>> getAllPasajeros() {
        return ResponseEntity.ok(pasajeroService.buscarPasajeros());
    }

    @GetMapping("/id")
    public ResponseEntity<PasajeroDto> getPasajeroById(@PathVariable Long id) {
        return pasajeroService.buscarPasajeroPorId(id)
                .map(pasajero -> ResponseEntity.ok().body(pasajero))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PasajeroDto> createPasajero(@RequestBody PasajeroDto pasajero) throws URISyntaxException {
        return crearNuevoPasajero(pasajero);
    }

    @PutMapping("/id")
    public ResponseEntity<PasajeroDto> actualizarPasajero(@PathVariable Long id, @RequestBody PasajeroDto nuevoPasajero) {
        Optional<PasajeroDto> pasajeroUpdate = pasajeroService.actualizarPasajero(id, nuevoPasajero);
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

    private ResponseEntity<PasajeroDto> crearNuevoPasajero(PasajeroDto pasajero) {
        PasajeroDto nuevoPasajero = pasajeroService.guardarPasajero(pasajero);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoPasajero.idPasajero())
                .toUri();
        return ResponseEntity.created(location).body(nuevoPasajero);
    }
}
