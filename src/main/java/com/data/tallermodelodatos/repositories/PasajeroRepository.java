package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
}
