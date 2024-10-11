package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Aerolinea;
import com.data.tallermodelodatos.dto.AeropuertoDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AeropuertoService {
    AeropuertoDto guardarAeropuerto(AeropuertoDto aeropuerto);
    Optional<AeropuertoDto> buscarAeropuertoPorId(Long id);
    List<AeropuertoDto> buscarAeropuertos();
    List<AeropuertoDto> buscarAeropuertosPorNombre(String nombre);
    List<AeropuertoDto> buscarAeropuertosPorIds(List<Long> ids);
    List<AeropuertoDto> buscarAeropuertosPorCiudad(String ciudad);
    List<AeropuertoDto> buscarAeropuertosPorPais(String pais);
    Optional<AeropuertoDto> actualizarAeropuerto(AeropuertoDto aeropuerto,Long id);
    void eliminarAeropuerto(Long id);
}
