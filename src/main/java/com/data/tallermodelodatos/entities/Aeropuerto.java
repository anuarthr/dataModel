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
@Table(name = "aeropuertos")
public class Aeropuerto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAeropuerto;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String pais;

    @OneToMany(mappedBy = "aeropuerto")
    private List<Vuelo> vuelos = new ArrayList<>();
}
