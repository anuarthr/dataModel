package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Aerolinea;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AerolineaService {
    Aerolinea guardarAerolinea(Aerolinea aerolinea);
    Optional<Aerolinea> buscarAerolineaPorId(Long id);
    List<Aerolinea> buscarAerolineas();
    List<Aerolinea> buscarAerolineasPorNombre(String nombre);
    List<Aerolinea> buscarAerolineasPorIds(List<Long> ids);
    Aerolinea buscarAerolineaPorCodigo(Long codigo);
    List<Aerolinea> buscarAerolineasPorPaisDeOrigen(String paisDeOrigen);
    Aerolinea actualizarAerolinea(Aerolinea aerolinea);
    Optional<Aerolinea> actualizarAerolinea(Long id, Aerolinea aerolinea);
    void eliminarAerolinea(Long id);
}
