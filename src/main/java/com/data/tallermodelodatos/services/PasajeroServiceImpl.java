package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Pasajero;
import com.data.tallermodelodatos.repositories.PasajeroRepository;

import java.util.List;
import java.util.Optional;

public class PasajeroServiceImpl implements PasajeroService{

    private PasajeroRepository pasajeroRepository;

    public PasajeroServiceImpl(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }

    @Override
    public Pasajero guardarPasajero(Pasajero pasajero) {return pasajeroRepository.save(pasajero);}

    @Override
    public Optional<Pasajero> buscarPasajeroPorId(Long id) {return pasajeroRepository.findById(id);}

    @Override
    public List<Pasajero> buscarPasajeros() {return pasajeroRepository.findAll();}

    @Override
    public List<Pasajero> buscarPasajerosPorNombre(String nombre) {return pasajeroRepository.findByNombre(nombre);}

    @Override
    public List<Pasajero> buscarPasajerosPorIds(List<Long> ids) {return pasajeroRepository.findByIdIn(ids);}

    @Override
    public List<Pasajero> buscarPasajerosPorApellido(String apellido) {return pasajeroRepository.findByApellido(apellido);}

    @Override
    public Pasajero buscarPasajeroPorPasaporte(Long pasaporte) {return pasajeroRepository.findByPasaporte(pasaporte);}

    @Override
    public List<Pasajero> buscarPasajerosPorNacionalidad(String nacionalidad) {return pasajeroRepository.findByNacionalidad(nacionalidad);}

    @Override
    public Pasajero actualizarPasajero(Pasajero pasajero) {return pasajeroRepository.save(pasajero);}

    @Override
    public Optional<Pasajero> actualizarPasajero(Long id, Pasajero pasajero) {
        return pasajeroRepository.findById(id).map(oldPasajero -> {
            oldPasajero.setNombre(pasajero.getNombre());
            oldPasajero.setApellido(pasajero.getApellido());
            oldPasajero.setPasaporte(pasajero.getPasaporte());
            oldPasajero.setNacionalidad(pasajero.getNacionalidad());
            oldPasajero.setReserva(pasajero.getReserva());
            return pasajeroRepository.save(oldPasajero);
        });
    }

    @Override
    public void eliminarPasajero(Long id) {
        pasajeroRepository.deleteById(id);
    }
}