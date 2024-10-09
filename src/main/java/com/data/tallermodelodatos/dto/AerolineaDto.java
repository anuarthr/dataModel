package com.data.tallermodelodatos.dto;

import java.util.List;

public record AerolineaDto(Long idAerolinea, String nombre, Long codigoAerolinea, String paisDeOrigen,
                           List<VueloDto> vuelos) {
}
