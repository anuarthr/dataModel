package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
