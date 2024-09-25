package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Vuelo;
import com.data.tallermodelodatos.repositories.VueloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class VueloServiceImpl implements VueloService {

    private final VueloRepository vueloRepository;

    public VueloServiceImpl(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    @Override
    public Vuelo guardarVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    public Optional<Vuelo> buscarVueloPorId(Long id) {
        return vueloRepository.findById(id);
    }

    @Override
    public List<Vuelo> buscarVuelos() {
        return vueloRepository.findAll();
    }

    @Override
    public List<Vuelo> buscarVuelosPorIds(List<Long> ids) {
        return vueloRepository.findAllById(ids);
    }

    @Override
    public List<Vuelo> buscarVuelosPorOrigen(String origen) {
        return vueloRepository.findByOrigen(origen);
    }

    @Override
    public List<Vuelo> buscarVuelosPorDestino(String destino) {
        return vueloRepository.findByDestino(destino);
    }

    @Override
    public Vuelo actualizarVuelo(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    public Optional<Vuelo> actualizarVuelo(Long id, Vuelo vuelo) {
        return vueloRepository.findById(id).map(oldVuelo -> {
            oldVuelo.setOrigen(vuelo.getOrigen());
            oldVuelo.setDestino(vuelo.getDestino());
            oldVuelo.setFechaDeSalida(vuelo.getFechaDeSalida());
            oldVuelo.setHoraDeSalida(vuelo.getHoraDeSalida());
            oldVuelo.setDuracion(vuelo.getDuracion());
            oldVuelo.setCapacidad(vuelo.getCapacidad());
            oldVuelo.setAerolinea(vuelo.getAerolinea());
            oldVuelo.setAeropuerto(vuelo.getAeropuerto());
            return vueloRepository.save(oldVuelo);
        });
    }

    @Override
    public void deleteVuelo(Long id) {
        vueloRepository.deleteById(id);
    }
}
