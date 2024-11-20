package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.AerolineaDto;
import com.data.tallermodelodatos.exception.AerolineaNotFoundException;
import com.data.tallermodelodatos.services.AerolineaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aerolineas")
@CrossOrigin(origins = "http://localhost:5173")

public class AerolineaController {

        private final AerolineaService aerolineaService;


        public AerolineaController(AerolineaService aerolineaService) {
            this.aerolineaService = aerolineaService;
        }

        @GetMapping()
        public ResponseEntity<List<AerolineaDto>> getAllAerolinea() {
            return ResponseEntity.ok(aerolineaService.buscarAerolineas());
        }

        @GetMapping("/{id}")
        public ResponseEntity<AerolineaDto> getAerolineaById(@PathVariable Long id) {
            return aerolineaService.buscarAerolineaPorId(id)
                    .map(aerolinea -> ResponseEntity.ok().body(aerolinea))
                    .orElseThrow(AerolineaNotFoundException::new);
        }

        @PostMapping
        public ResponseEntity<AerolineaDto> createAerolinea(@RequestBody AerolineaDto aerolinea) throws URISyntaxException {
            return crearNuevoAerolinea(aerolinea);
        }

        @PutMapping("/{id}")
        public ResponseEntity<AerolineaDto> actualizarAerolinea(@PathVariable Long id, @RequestBody AerolineaDto nuevoAerolinea){
            Optional<AerolineaDto> aerolineaUpdate = aerolineaService.actualizarAerolinea(id, nuevoAerolinea);
            return aerolineaUpdate.map(aerolinea -> ResponseEntity.ok(aerolinea))
                    .orElseGet(() -> {
                        return crearNuevoAerolinea(nuevoAerolinea);
                    });
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAerolinea(@PathVariable Long id) {
            aerolineaService.eliminarAerolinea(id);
            return ResponseEntity.noContent().build();
        }

        private ResponseEntity<AerolineaDto> crearNuevoAerolinea(AerolineaDto aerolinea) {
            AerolineaDto nuevoAerolinea = aerolineaService.guardarAerolinea(aerolinea);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(nuevoAerolinea.idAerolinea())
                    .toUri();
            return ResponseEntity.created(location).body(nuevoAerolinea);
        }
}
