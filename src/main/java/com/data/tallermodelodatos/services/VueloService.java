package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.VueloDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface VueloService {
    VueloDto guardarVuelo(VueloDto vuelo);
    Optional<VueloDto> buscarVueloPorId(Long id);
    List<VueloDto> buscarVuelos();
    List<VueloDto> buscarVuelosPorOrigen(String origen);
    List<VueloDto> buscarVuelosPorDestino(String destino);
    List<VueloDto> buscarVuelosPorIds(List<Long> ids);
    Optional<VueloDto> actualizarVuelo(Long id, VueloDto vuelo);
    void eliminarVuelo(Long id);
}