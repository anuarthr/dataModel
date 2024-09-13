package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Aerolinea;
import com.data.tallermodelodatos.entities.Aeropuerto;

import java.util.List;
import java.util.Optional;

public interface AeropuertoService {
    Aeropuerto guardarAeropuerto(Aeropuerto aeropuerto);
    Optional<Aeropuerto> buscarAeropuertoPorId(Long id);
    List<Aeropuerto> buscarAeropuertos();
    List<Aeropuerto> buscarAeropuertosPorNombre(String nombre);
    List<Aeropuerto> buscarAeropuertosPorIds(List<Long> ids);
    List<Aeropuerto> buscarAeropuertosPorCiudad(String ciudad);
    List<Aeropuerto> buscarAeropuertosPorPais(String pais);
    Aeropuerto actualizarAeropuerto(Aeropuerto aeropuerto);
    Optional<Aeropuerto> actualizarAeropuerto(Long id, Aeropuerto aeropuerto);
}
