package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.ReservaDto;
import com.data.tallermodelodatos.dto.ReservaMapper;
import com.data.tallermodelodatos.entities.Reserva;
import com.data.tallermodelodatos.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

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
    public ReservaDto guardarReserva(ReservaDto reservaDto) {
        Reserva reserva = reservaMapper.reservaDtoWithoutIdToReserva(reservaDto);
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
    public List<ReservaDto> buscarReservasPorCliente(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId).stream()
                .map(reservaMapper::reservaToReservaDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReservaDto> actualizarReserva(Long id, ReservaDto reservaDto) {
        return reservaRepository.findById(id).map(oldReserva -> {
            oldReserva.setFechaDeReserva(reservaDto.fechaDeReserva());
            oldReserva.setVuelos(reservaMapper.vueloDtosToVuelos(reservaDto.vuelos()));
            oldReserva.setCliente(reservaMapper.clienteDtoToCliente(reservaDto.cliente()));
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
