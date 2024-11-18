package com.data.tallermodelodatos.repositories;

import com.data.tallermodelodatos.entities.ERole;
import com.data.tallermodelodatos.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}