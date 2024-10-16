package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    List<Vuelo> findByOrigen(String origen);
    List<Vuelo> findByDestino(String destino);
}
