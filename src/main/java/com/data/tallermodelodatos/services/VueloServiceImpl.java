package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.VueloMapper;
import com.data.tallermodelodatos.dto.VueloDto;
import com.data.tallermodelodatos.entities.Vuelo;
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
    private final VueloMapper vueloMapper;

    public VueloServiceImpl(VueloRepository vueloRepository, VueloMapper vueloMapper) {
        this.vueloRepository = vueloRepository;
        this.vueloMapper = vueloMapper;
    }

    @Override
    public VueloDto guardarVuelo(VueloDto vuelo) {
        Vuelo savedVuelo = vueloRepository.save(vueloMapper.vueloDtoToVuelo(vuelo));
        return vueloMapper.vueloToVueloDto(savedVuelo);
    }

    @Override
    public Optional<VueloDto> buscarVueloPorId(Long id) {
        return vueloRepository.findById(id).map(
                vueloMapper::vueloToVueloDto);
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
    public Optional<VueloDto> actualizarVuelo(Long id, VueloDto vuelo) {
        return vueloRepository.findById(id).map(oldVuelo -> {
            oldVuelo.setOrigen(vuelo.origen());
            oldVuelo.setDestino(vuelo.destino());
            oldVuelo.setFechaDeSalida(vuelo.fechaDeSalida());
            oldVuelo.setHoraDeSalida(vuelo.horaDeSalida());
            oldVuelo.setDuracion(vuelo.duracion());
            oldVuelo.setCapacidad(vuelo.capacidad());
            oldVuelo.setAerolinea(vueloMapper.aerolineaDtoToAerolinea(vuelo.aerolineaId()));
            oldVuelo.setAeropuerto(vueloMapper.aeropuertoDtoToAeropuerto(vuelo.aeropuertoId()));
            return vueloMapper.vueloToVueloDto(vueloRepository.save(oldVuelo));
        });
    }

    @Override
    public void eliminarVuelo(Long id) {
        vueloRepository.deleteById(id);
    }
}