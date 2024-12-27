package com.aldev.accountservice.repository;

import com.aldev.accountservice.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    boolean existsByIdentificacion(String identificacion);
}
