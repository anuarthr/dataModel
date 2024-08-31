package com.data.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "vuelos")
@Data
public class Vuelo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVuelo;

    private String origen;
    private String destino;
    private String fechaDeSalida;
    private String horaDeSalida;
    private Integer duracion;
    private Integer capacidad;

    /*@ManyToOne
    @JoinColumn(name = "idAerolinea")
    private Aerolinea aerolinea;

    @ManyToOne
    @JoinColumn(name = "origen")
    private Aeropuerto aeropuertoOrigen;

    @ManyToOne
    @JoinColumn(name = "destino")
    private Aeropuerto aeropuertoDestino;*/

}
