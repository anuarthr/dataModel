package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.VueloDto;
import com.data.tallermodelodatos.services.VueloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vuelos")

public class VueloController {

    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping()
    public ResponseEntity<List<VueloDto>> getAllVuelos() {
        return ResponseEntity.ok(vueloService.buscarVuelos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VueloDto> getVueloById(@PathVariable Long id) {
        return vueloService.buscarVueloPorId(id)
                .map(vuelo -> ResponseEntity.ok().body(vuelo))
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
    }

    @PostMapping()
    public ResponseEntity<VueloDto> createVuelo(@RequestBody VueloDto vuelo) throws URISyntaxException {
        VueloDto newVuelo = vueloService.guardarVuelo(vuelo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVuelo.idVuelo())
                .toUri();
        return ResponseEntity.created(location).body(newVuelo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VueloDto> updateVuelo(@PathVariable Long id, @RequestBody VueloDto newVuelo) {
        Optional<VueloDto> vueloUpdated = vueloService.actualizarVuelo(id, newVuelo);
        return vueloUpdated.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    VueloDto createdVuelo = vueloService.guardarVuelo(newVuelo);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(createdVuelo.idVuelo())
                            .toUri();
                    return ResponseEntity.created(location).body(createdVuelo);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVuelo(@PathVariable Long id) {
        vueloService.deleteVuelo(id);
        return ResponseEntity.noContent().build();
    }
}
