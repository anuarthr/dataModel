package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.entities.Vuelo;
import com.data.tallermodelodatos.services.ClienteServiceImpl;
import com.data.tallermodelodatos.services.VueloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vuelos")

public class VueloController {
    private final VueloService vueloService;

    public VueloController(VueloService vueloService, ClienteServiceImpl clienteServiceImpl){
        this.vueloService = vueloService;
    }
    @GetMapping()
    public ResponseEntity<List<Vuelo>> getAllVuelos(){
        return ResponseEntity.ok(vueloService.buscarVuelos());
    }
    @GetMapping("/idVuelo")
    public ResponseEntity<Vuelo> getVueloById(@PathVariable("idVuelo") Long id){
        return vueloService.buscarVueloPorId(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Vuelo> createVuelo(@RequestBody Vuelo vuelo){
        return createNewVuelo(vuelo);
    }
    @PutMapping("/id")
    public ResponseEntity<Vuelo> updateVuelo(@PathVariable Long id,@RequestBody Vuelo vuelo) {
        Optional<Vuelo> vueloUpdated = vueloService.actualizarVuelo(id, vuelo);
        return vueloUpdated
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(()->{
                    return createNewVuelo(vuelo);
                });
    }
    private ResponseEntity<Vuelo> createNewVuelo(Vuelo vuelo) {
        Vuelo newVuelo = vueloService.guardarVuelo(vuelo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVuelo.getIdVuelo())
                .toUri();
        return ResponseEntity.created(location).body(newVuelo);
    }
    @DeleteMapping("/id")
    public ResponseEntity<Vuelo> deleteVuelo(@PathVariable Long id){
        vueloService.deleteVuelo(id);
        return ResponseEntity.noContent().build();
    }
}
