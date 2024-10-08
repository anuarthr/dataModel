package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.VueloDto;
import com.data.tallermodelodatos.dto.VueloMapper;
import com.data.tallermodelodatos.entities.Vuelo;
import com.data.tallermodelodatos.services.VueloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vuelos")
public class VueloController {

    private final VueloService vueloService;
    private final VueloMapper vueloMapper;

    public VueloController(VueloService vueloService, VueloMapper vueloMapper) {
        this.vueloService = vueloService;
        this.vueloMapper = vueloMapper;
    }

    @GetMapping
    public ResponseEntity<List<VueloDto>> getAllVuelos() {
        List<VueloDto> vuelosDto = vueloService.buscarVuelos().stream()
                .map(vueloMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vuelosDto);
    }

    @GetMapping("/{idVuelo}")
    public ResponseEntity<VueloDto> getVueloById(@PathVariable("idVuelo") Long id) {
        return vueloService.buscarVueloPorId(id)
                .map(vueloMapper::toDto)
                .map(vueloDto -> ResponseEntity.ok().body(vueloDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VueloDto> createVuelo(@RequestBody VueloDto vueloDto) {
        Vuelo vuelo = vueloMapper.toVuelo(vueloDto);
        Vuelo newVuelo = vueloService.guardarVuelo(vuelo);
        VueloDto newVueloDto = vueloMapper.toDto(newVuelo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVueloDto.idVuelo())
                .toUri();
        return ResponseEntity.created(location).body(newVueloDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VueloDto> updateVuelo(@PathVariable Long id, @RequestBody VueloDto vueloDto) {
        Vuelo vuelo = vueloMapper.toVuelo(vueloDto);
        Optional<Vuelo> vueloUpdated = vueloService.actualizarVuelo(id, vuelo);

        return vueloUpdated
                .map(v -> ResponseEntity.ok(vueloMapper.toDto(v)))
                .orElseGet(() -> createVuelo(vueloDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVuelo(@PathVariable Long id) {
        vueloService.deleteVuelo(id);
        return ResponseEntity.noContent().build();
    }
}
