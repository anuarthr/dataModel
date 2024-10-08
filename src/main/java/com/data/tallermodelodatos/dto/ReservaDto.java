package com.data.tallermodelodatos.dto;

import java.time.LocalDate;
import java.util.List;

public record ReservaDto(Long idReserva, ClienteDto cliente, List<VueloDto> vuelos,
                         LocalDate fechaDeReserva, Integer numeroDePasajeros) {

}
