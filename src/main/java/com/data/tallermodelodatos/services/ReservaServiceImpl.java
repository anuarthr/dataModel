package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ReservaDto;
import com.data.tallermodelodatos.dto.ReservaMapper;
import com.data.tallermodelodatos.entities.Cliente;
import com.data.tallermodelodatos.entities.Reserva;
import com.data.tallermodelodatos.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    public ReservaServiceImpl(ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }

    @Override
    public ReservaDto guardarReserva(ReservaDto reservaDto, Cliente cliente) {
        Reserva reserva = reservaMapper.reservaDtoToReserva(reservaDto);
        reserva.setCliente(cliente);
        Reserva savedReserva = reservaRepository.save(reserva);
        return reservaMapper.reservaToReservaDto(savedReserva);
    }

    @Override
    public Optional<ReservaDto> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .map(reservaMapper::reservaToReservaDto);
    }

    @Override
    public List<ReservaDto> buscarReservas() {
        return reservaRepository.findAll().stream()
                .map(reservaMapper::reservaToReservaDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservaDto> buscarReservasPorCliente(Cliente cliente) {
        List<ReservaDto> reservasC = new ArrayList<>();
        reservaRepository.findByCliente(cliente).forEach(
                reserva -> reservasC.add(reservaMapper.reservaToReservaDtoWithoutId(reserva))
        );
        return reservasC;
    }

    @Override
    public Optional<ReservaDto> actualizarReserva(Long id, ReservaDto reservaDto) {
        return reservaRepository.findById(id).map(oldReserva -> {
            oldReserva.setFechaDeReserva(reservaDto.fechaDeReserva());
            oldReserva.setNumeroDePasajeros(reservaDto.numeroDePasajeros());
            Reserva updatedReserva = reservaRepository.save(oldReserva);
            return reservaMapper.reservaToReservaDto(updatedReserva);
        });
    }

    @Override
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}