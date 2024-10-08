package com.data.tallermodelodatos.api;
import com.data.tallermodelodatos.dto.ReservaDto;
import com.data.tallermodelodatos.dto.ReservaMapper;
import com.data.tallermodelodatos.entities.Reserva;
import com.data.tallermodelodatos.services.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final ReservaMapper reservaMapper;

    public ReservaController(ReservaService reservaService, ReservaMapper reservaMapper) {
        this.reservaService = reservaService;
        this.reservaMapper = reservaMapper;
    }

    @GetMapping
    public ResponseEntity<List<ReservaDto>> getAllReservas() {
        List<ReservaDto> reservasDto = reservaService.buscarReservas().stream()
                .map(reservaMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservasDto);
    }

    @GetMapping("/{idReserva}")
    public ResponseEntity<ReservaDto> getReservaById(@PathVariable("idReserva") Long id) {
        return reservaService.buscarReservaPorId(id)
                .map(reservaMapper::toDto)
                .map(reservaDto -> ResponseEntity.ok().body(reservaDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReservaDto> createReserva(@RequestBody ReservaDto reservaDto) {
        Reserva nuevaReserva = reservaMapper.toReserva(reservaDto);
        Reserva reservaGuardada = reservaService.guardarReserva(nuevaReserva);
        ReservaDto reservaGuardadaDto = reservaMapper.toDto(reservaGuardada);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reservaGuardadaDto.idReserva())
                .toUri();
        return ResponseEntity.created(location).body(reservaGuardadaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable Long id, @RequestBody ReservaDto reservaDto) {
        Reserva reserva = reservaMapper.toReserva(reservaDto);
        Optional<Reserva> reservaActualizada = reservaService.actualizarReserva(id, reserva);

        return reservaActualizada
                .map(r -> ResponseEntity.ok(reservaMapper.toDto(r)))
                .orElseGet(() -> createReserva(reservaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}

