package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.PasajeroDto;
import com.data.tallermodelodatos.dto.PasajeroMapper;
import com.data.tallermodelodatos.repositories.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class PasajeroServiceImpl implements PasajeroService{

    @Autowired
    private PasajeroRepository pasajeroRepository;
    private final PasajeroMapper pasajeroMapper;

    public PasajeroServiceImpl(PasajeroRepository pasajeroRepository, PasajeroMapper pasajeroMapper) {
        this.pasajeroRepository = pasajeroRepository;
        this.pasajeroMapper = pasajeroMapper;
    }

    @Override
    public PasajeroDto guardarPasajero(PasajeroDto pasajero) {
        return pasajeroMapper.pasajeroToPasajeroDtoWithoutId(pasajeroRepository.save(pasajeroMapper.pasajeroDtoWithoutIdToPasajero(pasajero)));}

    @Override
    public Optional<PasajeroDto> buscarPasajeroPorId(Long id) {
        return pasajeroRepository.findById(id).map(
                pasajero -> pasajeroMapper.pasajeroToPasajeroDto(pasajero));}

    @Override
    public List<PasajeroDto> buscarPasajeros() {
        List<PasajeroDto> pasajeros = new ArrayList<>();
        pasajeroRepository.findAll().forEach(pasajero -> pasajeros.add(pasajeroMapper.pasajeroToPasajeroDtoWithoutId(pasajero)));
        return pasajeros;}

    @Override
    public List<PasajeroDto> buscarPasajerosPorNombre(String nombre) {
        List<PasajeroDto> pasajeros = new ArrayList<>();
        pasajeroRepository.findAllByNombre(nombre).forEach(pasajero -> pasajeros.add(pasajeroMapper.pasajeroToPasajeroDtoWithoutId(pasajero)));
        return pasajeros;

    }

    @Override
    public List<PasajeroDto> buscarPasajerosPorIds(List<Long> ids) {
        List<PasajeroDto> pasajeros = new ArrayList<>();
        pasajeroRepository.findByIdIn(ids).forEach(pasajero -> pasajeros.add(pasajeroMapper.pasajeroToPasajeroDtoWithoutId(pasajero)));
        return pasajeros;
    }

    @Override
    public List<PasajeroDto> buscarPasajerosPorApellido(String apellido) {
        List<PasajeroDto> pasajeros = new ArrayList<>();
        pasajeroRepository.findAllByApellido(apellido).forEach(pasajero -> pasajeros.add(pasajeroMapper.pasajeroToPasajeroDtoWithoutId(pasajero)));
        return pasajeros;}

    @Override
    public Optional<PasajeroDto> buscarPasajeroPorPasaporte(Long pasaporte) {
        return pasajeroRepository.findByPasaporteIn(pasaporte).map(pasajero -> pasajeroMapper.pasajeroToPasajeroDto(pasajero));
    }

    @Override
    public List<PasajeroDto> buscarPasajerosPorNacionalidad(String nacionalidad) {
        List<PasajeroDto> pasajeros = new ArrayList<>();
        pasajeroRepository.findAllByNacionalidad(nacionalidad).forEach(pasajero -> pasajeros.add(pasajeroMapper.pasajeroToPasajeroDtoWithoutId(pasajero)));
        return pasajeros;
    }

    @Override
    public Optional<PasajeroDto> actualizarPasajero(Long id, PasajeroDto pasajero) {
        return pasajeroRepository.findById(id).map(oldPasajero -> {
            oldPasajero.setNombre(pasajero.nombre());
            oldPasajero.setApellido(pasajero.apellido());
            oldPasajero.setNacionalidad(pasajero.nacionalidad());
            oldPasajero.setPasaporte(pasajero.pasaporte());
            oldPasajero.setNacionalidad(pasajero.nacionalidad());
            return pasajeroMapper.pasajeroToPasajeroDto(pasajeroRepository.save(oldPasajero));
        });
    }

    @Override
    public void eliminarPasajero(Long id) {
        pasajeroRepository.deleteById(id);
    }
}