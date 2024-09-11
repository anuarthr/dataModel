package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    Reserva guardarReserva(Reserva reserva);
    Optional<Reserva> buscarReservaPorId(Long id);
    List<Reserva> buscarReservas();
    List<Reserva> buscarReservasPorCliente(Long clienteId);
    Reserva actualizarReserva(Reserva reserva);
    Optional<Reserva> actualizarReserva(Long id, Reserva reserva);
}
