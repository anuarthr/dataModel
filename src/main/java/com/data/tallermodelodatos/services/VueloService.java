package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.dto.VueloDto;
import java.util.List;
import java.util.Optional;

public interface VueloService {
    VueloDto guardarVuelo(VueloDto vueloDto);
    Optional<VueloDto> buscarVueloPorId(Long id);
    List<VueloDto> buscarVuelos();
    List<VueloDto> buscarVuelosPorIds(List<Long> ids);
    List<VueloDto> buscarVuelosPorOrigen(String origen);
    List<VueloDto> buscarVuelosPorDestino(String destino);
    Optional<VueloDto> actualizarVuelo(Long id, VueloDto vueloDto);
    void deleteVuelo(Long id);
}
