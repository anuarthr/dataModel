package com.data.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;
    private String direccion;
    private String telefono;

    @Column(nullable = false)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaNacimiento;
}
