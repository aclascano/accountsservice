package com.aldev.accountservice.repository;

import com.aldev.accountservice.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByIdentificacion(String identificacion);
}