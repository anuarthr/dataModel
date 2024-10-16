package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.PasajeroDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface PasajeroService {
    PasajeroDto guardarPasajero(PasajeroDto pasajero);
    Optional<PasajeroDto> buscarPasajeroPorId(Long id);
    List<PasajeroDto> buscarPasajeros();
    List<PasajeroDto> buscarPasajerosPorNombre(String nombre);
    List<PasajeroDto> buscarPasajerosPorIds(List<Long> ids);
    List<PasajeroDto> buscarPasajerosPorApellido(String apellido);
    Optional<PasajeroDto> buscarPasajeroPorPasaporte(Long pasaporte);
    List<PasajeroDto> buscarPasajerosPorNacionalidad(String nacionalidad);
    Optional<PasajeroDto> actualizarPasajero(Long id, PasajeroDto pasajero);
    void eliminarPasajero(Long id);
}
