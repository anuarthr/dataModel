package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.AerolineaDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AerolineaService {
    AerolineaDto guardarAerolinea(AerolineaDto aerolinea);
    Optional<AerolineaDto> buscarAerolineaPorId(Long id);
    List<AerolineaDto> buscarAerolineas();
    List<AerolineaDto> buscarAerolineasPorNombre(String nombre);
    List<AerolineaDto> buscarAerolineasPorIds(List<Long> ids);
    AerolineaDto buscarAerolineaPorCodigo(Long codigo);
    List<AerolineaDto> buscarAerolineasPorPaisDeOrigen(String paisDeOrigen);
    AerolineaDto actualizarAerolinea(AerolineaDto aerolinea);
    Optional<AerolineaDto> actualizarAerolinea(Long id, AerolineaDto aerolinea);
    void eliminarAerolinea(Long id);
}
