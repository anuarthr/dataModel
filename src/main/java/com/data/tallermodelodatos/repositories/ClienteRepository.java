package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombre(String nombre);
    List<Cliente> findByIdClienteIn(Collection<Long> ids);
}
