package com.data.tallermodelodatos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCliente;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;
    private String direccion;
    private String telefono;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "cliente")
    private ArrayList<Reserva> reservas = new ArrayList<>();
}
