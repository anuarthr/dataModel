package com.data.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "aerolineas")
@Data
public class Aerolinea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAerolinea;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Long codigoAerolinea;

    @Column(nullable = false)
    private String paisDeOrigen;
}
