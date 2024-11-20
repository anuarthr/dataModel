package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.AeropuertoDto;
import com.data.tallermodelodatos.exception.AerolineaNotFoundException;
import com.data.tallermodelodatos.services.AeropuertoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aeropuertos")
@CrossOrigin(origins = "http://localhost:5173")

public class AeropuertoController {

    private final AeropuertoService aeropuertoService;


    public AeropuertoController(AeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }

    @GetMapping()
    public ResponseEntity<List<AeropuertoDto>> getAllAeropuerto() {
        return ResponseEntity.ok(aeropuertoService.buscarAeropuertos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AeropuertoDto> getAeropuertoById(@PathVariable Long id) {
        return aeropuertoService.buscarAeropuertoPorId(id)
                .map(aeropuerto -> ResponseEntity.ok().body(aeropuerto))
                .orElseThrow(AerolineaNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<AeropuertoDto> createAeropuerto(@RequestBody AeropuertoDto aeropuerto) throws URISyntaxException {
        return crearNuevoAeropuerto(aeropuerto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AeropuertoDto> actualizarAeropuerto(@PathVariable Long id, @RequestBody AeropuertoDto nuevoAeropuerto) throws URISyntaxException {
        Optional<AeropuertoDto> aeropuertoUpdate = aeropuertoService.actualizarAeropuerto(id,nuevoAeropuerto);
        return aeropuertoUpdate.map(aeropuerto -> ResponseEntity.ok(aeropuerto))
                .orElseGet(() -> {
                    return crearNuevoAeropuerto(nuevoAeropuerto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAeropuerto(@PathVariable Long id) {
        aeropuertoService.eliminarAeropuerto(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<AeropuertoDto> crearNuevoAeropuerto(AeropuertoDto aeropuerto) {
        AeropuertoDto nuevoAeropuerto = aeropuertoService.guardarAeropuerto(aeropuerto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoAeropuerto.idAeropuerto())
                .toUri();
        return ResponseEntity.created(location).body(nuevoAeropuerto);
    }
}
