package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.Vuelo;

import java.util.List;

public record AeropuertoDto(Long idAeropuerto, String nombre, String ciudad, String pais) {
}
