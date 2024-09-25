package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.entities.Reserva;
import com.data.tallermodelodatos.services.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservas")

public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        return ResponseEntity.ok(reservaService.buscarReservas());
    }
    @GetMapping("/idReserva")
    public ResponseEntity<Reserva> getReservaById(@PathVariable("idReserva") Long id){
        return reservaService.buscarReservaPorId(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva){
        return createNewReserva(reserva);
        /*Reserva newReserva = reservaService.guardarReserva(reserva);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newReserva.getIdReserva())
                .toUri();
        return ResponseEntity.created(location).body(newReserva);*/
    }
    @PutMapping("/id")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reserva){
        Optional<Reserva> reservaUpdated = reservaService.actualizarReserva(id, reserva);
        return reservaUpdated
                .map(c -> ResponseEntity.ok(c))
                .orElseGet(()->{
                    return createNewReserva(reserva);
                });
    }
    private ResponseEntity<Reserva> createNewReserva(Reserva reserva){
        Reserva newReserva = reservaService.guardarReserva(reserva);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newReserva.getIdReserva())
                .toUri();
        return ResponseEntity.created(location).body(newReserva);
    }
    @DeleteMapping("/id")
    public ResponseEntity<Reserva> deleteReserva(@PathVariable Long id){
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
