package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Reserva;
import com.data.tallermodelodatos.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReservaServiceImpl implements ReservaService {

    private ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public List<Reserva> buscarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> buscarReservasPorCliente(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }

    @Override
    public Reserva actualizarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> actualizarReserva(Long id, Reserva reserva) {
        return reservaRepository.findById(id).map(oldReserva -> {
            oldReserva.setFechaDeReserva(reserva.getFechaDeReserva());
            oldReserva.setVuelos(reserva.getVuelos());
            oldReserva.setCliente(reserva.getCliente());
            oldReserva.setNumeroDePasajeros(reserva.getNumeroDePasajeros());
            return reservaRepository.save(oldReserva);
        });
    }

    @Override
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

}
