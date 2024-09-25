package com.data.tallermodelodatos.services;

import com.data.tallermodelodatos.entities.Vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloService {
    Vuelo guardarVuelo(Vuelo vuelo);
    Optional<Vuelo> buscarVueloPorId(Long id);
    List<Vuelo> buscarVuelos();
    List<Vuelo> buscarVuelosPorIds(List<Long> ids);
    List<Vuelo> buscarVuelosPorOrigen(String origen);
    List<Vuelo> buscarVuelosPorDestino(String destino);
    Vuelo actualizarVuelo(Vuelo vuelo);
    Optional<Vuelo> actualizarVuelo(Long id, Vuelo vuelo);
    void deleteVuelo(Long id);
}
