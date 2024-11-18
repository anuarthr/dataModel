package com.data.tallermodelodatos.dto;

import java.util.List;

public record VueloDto(Long idVuelo, String origen, String destino, String fechaDeSalida,
                       String horaDeSalida, Integer duracion, Integer capacidad) {
}

