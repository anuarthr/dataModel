package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Cliente;
import com.data.tallermodelodatos.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByCliente(Cliente cliente);
}
