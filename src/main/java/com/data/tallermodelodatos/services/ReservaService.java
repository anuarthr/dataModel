package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ReservaDto;
import java.util.List;
import java.util.Optional;

public interface ReservaService {
    ReservaDto guardarReserva(ReservaDto reservaDto);
    Optional<ReservaDto> buscarReservaPorId(Long id);
    List<ReservaDto> buscarReservas();
    List<ReservaDto> buscarReservasPorCliente(Long clienteId);
    Optional<ReservaDto> actualizarReserva(Long id, ReservaDto reservaDto);
    void eliminarReserva(Long id);
}
