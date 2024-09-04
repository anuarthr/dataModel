package com.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "vuelos")
@Data
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


}
