package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Aeropuerto;
import com.data.tallermodelodatos.repositories.AeropuertoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AeropuertoServiceImpl implements AeropuertoService{

    private AeropuertoRepository aeropuertoRepository;

    public AeropuertoServiceImpl(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    @Override
    public Aeropuerto guardarAeropuerto(Aeropuerto aeropuerto) {return aeropuertoRepository.save(aeropuerto);}

    @Override
    public Optional<Aeropuerto> buscarAeropuertoPorId(Long id) {return aeropuertoRepository.findById(id);}

    @Override
    public List<Aeropuerto> buscarAeropuertos() {return aeropuertoRepository.findAll();}

    @Override
    public List<Aeropuerto> buscarAeropuertosPorNombre(String nombre) {return aeropuertoRepository.findByNombre(nombre);}

    @Override
    public List<Aeropuerto> buscarAeropuertosPorIds(List<Long> ids) {return aeropuertoRepository.findByIdIn(ids);}

    @Override
    public List<Aeropuerto> buscarAeropuertosPorCiudad(String ciudad) {return aeropuertoRepository.findByCiudad(ciudad);}

    @Override
    public List<Aeropuerto> buscarAeropuertosPorPais(String pais) {return aeropuertoRepository.findByPais(pais);}

    @Override
    public Aeropuerto actualizarAeropuerto(Aeropuerto aeropuerto) {return aeropuertoRepository.save(aeropuerto);}

    @Override
    public Optional<Aeropuerto> actualizarAeropuerto(Long id, Aeropuerto aeropuerto) {
        return aeropuertoRepository.findById(id).map(oldAeropuerto -> {
            oldAeropuerto.setNombre(aeropuerto.getNombre());
            oldAeropuerto.setCiudad(aeropuerto.getCiudad());
            oldAeropuerto.setPais(aeropuerto.getPais());
            oldAeropuerto.setVuelos(aeropuerto.getVuelos());
            return aeropuertoRepository.save(oldAeropuerto);
        });
    }

    @Override
    public void eliminarAeropuerto(Long id) {
        aeropuertoRepository.deleteById(id);
    }
}
