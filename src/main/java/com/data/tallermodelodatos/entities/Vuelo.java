package com.data.tallermodelodatos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "vuelos")
public class Vuelo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVuelo;

    private String origen;
    private String destino;
    private String fechaDeSalida;
    private String horaDeSalida;
    private Integer duracion;
    private Integer capacidad;

    @ManyToOne
    @JoinColumn(name = "aerolinea_id")
    private Aerolinea aerolinea;

    @ManyToOne
    @JoinColumn(name = "aeropuerto_id")
    private Aeropuerto aeropuerto;

    @ManyToMany(mappedBy = "vuelos")
    private List<Reserva> reservas = new ArrayList<>();
}
