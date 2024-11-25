package com.data.tallermodelodatos.dto;

public record VueloDto(Long idVuelo, String origen, String destino, String fechaDeSalida,
                       String horaDeSalida, Integer duracion, Integer capacidad,
                       Long aerolineaId, Long aeropuertoId) {
}