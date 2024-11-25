package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.VueloMapper;
import com.data.tallermodelodatos.dto.VueloDto;
import com.data.tallermodelodatos.entities.Aerolinea;
import com.data.tallermodelodatos.entities.Aeropuerto;
import com.data.tallermodelodatos.entities.Vuelo;
import com.data.tallermodelodatos.exception.AerolineaNotFoundException;
import com.data.tallermodelodatos.exception.AeropuertoNotFoundException;
import com.data.tallermodelodatos.repositories.AerolineaRepository;
import com.data.tallermodelodatos.repositories.AeropuertoRepository;
import com.data.tallermodelodatos.repositories.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {
    @Autowired
    private VueloRepository vueloRepository;
    @Autowired
    private AerolineaRepository aerolineaRepository;
    @Autowired
    private AeropuertoRepository aeropuertoRepository;
    private final VueloMapper vueloMapper;

    public VueloServiceImpl(VueloRepository vueloRepository, VueloMapper vueloMapper, AerolineaRepository aerolineaRepository, AeropuertoRepository aeropuertoRepository) {
        this.vueloRepository = vueloRepository;
        this.vueloMapper = vueloMapper;
        this.aerolineaRepository = aerolineaRepository;
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public VueloDto guardarVuelo(VueloDto vueloDto) {
        Vuelo vuelo = vueloMapper.vueloDtoToVuelo(vueloDto);
        Aerolinea aerolinea = aerolineaRepository.findById(vueloDto.aerolineaId()).orElseThrow(AerolineaNotFoundException::new);
        Aeropuerto aeropuerto = aeropuertoRepository.findById(vueloDto.aeropuertoId()).orElseThrow(AeropuertoNotFoundException::new);
        vuelo.setAerolinea(aerolinea);
        vuelo.setAeropuerto(aeropuerto);
        Vuelo savedVuelo = vueloRepository.save(vuelo);
        return vueloMapper.vueloToVueloDto(savedVuelo);
    }

    @Override
    public Optional<VueloDto> buscarVueloPorId(Long id) {
        return vueloRepository.findById(id).map(
                vuelo -> vueloMapper.vueloToVueloDto(vuelo));
    }

    @Override
    public List<VueloDto> buscarVuelos() {
        List<VueloDto> vuelos = new ArrayList<>();
        vueloRepository.findAll().forEach(
                vuelo -> vuelos.add(vueloMapper.vueloToVueloDto(vuelo)));
        return vuelos;
    }

    @Override
    public List<VueloDto> buscarVuelosPorOrigen(String origen) {
        List<VueloDto> vuelos = new ArrayList<>();
        vueloRepository.findByOrigen(origen).forEach(
                vuelo -> vuelos.add(vueloMapper.vueloToVueloDto(vuelo)));
        return vuelos;
    }

    @Override
    public List<VueloDto> buscarVuelosPorDestino(String destino) {
        List<VueloDto> vuelos = new ArrayList<>();
        vueloRepository.findByDestino(destino).forEach(
                vuelo -> vuelos.add(vueloMapper.vueloToVueloDto(vuelo)));
        return vuelos;
    }

    @Override
    public List<VueloDto> buscarVuelosPorIds(List<Long> ids) {
        List<VueloDto> vuelos = new ArrayList<>();
        vueloRepository.findByIdVueloIn(ids).forEach(
                vuelo -> vuelos.add(vueloMapper.vueloToVueloDto(vuelo)));
        return vuelos;
    }

    @Override
    public Optional<VueloDto> actualizarVuelo(Long id, VueloDto vueloDto) {
        return vueloRepository.findById(id).map(oldVuelo -> {
            oldVuelo.setOrigen(vueloDto.origen());
            oldVuelo.setDestino(vueloDto.destino());
            oldVuelo.setFechaDeSalida(vueloDto.fechaDeSalida());
            oldVuelo.setHoraDeSalida(vueloDto.horaDeSalida());
            oldVuelo.setDuracion(vueloDto.duracion());
            oldVuelo.setCapacidad(vueloDto.capacidad());
            Aerolinea aerolinea = aerolineaRepository.findById(vueloDto.aerolineaId()).orElseThrow(AerolineaNotFoundException::new);
            Aeropuerto aeropuerto = aeropuertoRepository.findById(vueloDto.aeropuertoId()).orElseThrow(AeropuertoNotFoundException::new);
            oldVuelo.setAerolinea(aerolinea);
            oldVuelo.setAeropuerto(aeropuerto);
            return vueloMapper.vueloToVueloDto(vueloRepository.save(oldVuelo));
        });
    }

    @Override
    public void eliminarVuelo(Long id) {
        vueloRepository.deleteById(id);
    }
}