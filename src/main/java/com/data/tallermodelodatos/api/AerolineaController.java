package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.entities.Aerolinea;
import com.data.tallermodelodatos.services.AerolineaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/aerolinea")
public class AerolineaController {

        private final AerolineaService aerolineaService;


        public AerolineaController(AerolineaService aerolineaService) {
            this.aerolineaService = aerolineaService;
        }

        @GetMapping()
        public ResponseEntity<List<Aerolinea>> getAllAerolinea() {
            return ResponseEntity.ok(aerolineaService.buscarAerolineas());
        }

        @GetMapping("/id")
        public ResponseEntity<Aerolinea> getAerolineaById(@PathVariable Long id) {
            return aerolineaService.buscarAerolineaPorId(id)
                    .map(aerolinea -> ResponseEntity.ok().body(aerolinea))
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<Aerolinea> createAerolinea(@RequestBody Aerolinea aerolinea) throws URISyntaxException {
            return crearNuevoAerolinea(aerolinea);
        }

        @PutMapping("/id")
        public ResponseEntity<Aerolinea> actualizarAerolinea(@PathVariable Long id, @RequestBody Aerolinea nuevoAerolinea) throws URISyntaxException {
            Optional<Aerolinea> aerolineaUpdate = aerolineaService.actualizarAerolinea(id, nuevoAerolinea);
            return aerolineaUpdate.map(aerolinea -> ResponseEntity.ok(aerolinea))
                    .orElseGet(() -> {
                        return crearNuevoAerolinea(nuevoAerolinea);
                    });
        }

        @DeleteMapping("/id")
        public ResponseEntity<Void> deleteAerolinea(@PathVariable Long id) {
            aerolineaService.eliminarAerolinea(id);
            return ResponseEntity.noContent().build();
        }

        private ResponseEntity<Aerolinea> crearNuevoAerolinea(Aerolinea aerolinea) {
            Aerolinea nuevoAerolinea = aerolineaService.guardarAerolinea(aerolinea);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(nuevoAerolinea.getIdAerolinea())
                    .toUri();
            return ResponseEntity.created(location).body(nuevoAerolinea);
        }
}
