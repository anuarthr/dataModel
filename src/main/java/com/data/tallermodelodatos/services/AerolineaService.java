package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Aerolinea;

import java.util.List;
import java.util.Optional;

public interface AerolineaService {
    Aerolinea guardarAerolinea(Aerolinea aerolinea);
    Optional<Aerolinea> buscarAerolineaPorId(Long id);
    List<Aerolinea> buscarAerolineas();
    List<Aerolinea> buscarAerolineasPorNombre(String nombre);
    List<Aerolinea> buscarAerolineasPorIds(List<Long> ids);
    Aerolinea buscarAerolineaPorCodigo(Long codigo);
    Aerolinea buscarAerolineaPorPaisDeOrigen(String paisDeOrigen);
    Aerolinea actualizarAerolinea(Aerolinea aerolinea);
    Optional<Aerolinea> actualizarAerolinea(Long id, Aerolinea aerolinea);
}
