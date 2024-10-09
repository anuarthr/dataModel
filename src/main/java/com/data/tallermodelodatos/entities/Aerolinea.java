package com.data.tallermodelodatos.entities;

import com.data.tallermodelodatos.dto.AerolineaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "aerolineas")
public class Aerolinea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAerolinea;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Long codigoAerolinea;

    @Column(nullable = false)
    private String paisDeOrigen;

    @OneToMany(mappedBy = "aerolinea")
    private List<Vuelo> vuelos = new ArrayList<>();
}
