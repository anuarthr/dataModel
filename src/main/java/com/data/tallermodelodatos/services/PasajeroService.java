package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Aerolinea;
import com.data.tallermodelodatos.entities.Aeropuerto;
import com.data.tallermodelodatos.entities.Pasajero;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {
    Pasajero guardarPasajero(Pasajero pasajero);
    Optional<Pasajero> buscarPasajeroPorId(Long id);
    List<Pasajero> buscarPasajeros();
    List<Pasajero> buscarPasajerosPorNombre(String nombre);
    List<Pasajero> buscarPasajerosPorIds(List<Long> ids);
    List<Pasajero> buscarPasajerosPorApellido(String apellido);
    Pasajero buscarPasajeroPorPasaporte(Long pasaporte);
    List<Pasajero> buscarPasajerosPorNacionalidad(String nacionalidad);
    Pasajero actualizarPasajero(Pasajero pasajero);
    Optional<Pasajero> actualizarPasajero(Long id, Pasajero pasajero);
}
