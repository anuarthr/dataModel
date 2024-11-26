package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ReservaDto;
import com.data.tallermodelodatos.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    ReservaDto guardarReserva(ReservaDto reservaDto, Cliente cliente);
    Optional<ReservaDto> buscarReservaPorId(Long id);
    List<ReservaDto> buscarReservas();
    List<ReservaDto> buscarReservasPorCliente(Cliente cliente);
    Optional<ReservaDto> actualizarReserva(Long id, ReservaDto reservaDto);
    void eliminarReserva(Long id);
}