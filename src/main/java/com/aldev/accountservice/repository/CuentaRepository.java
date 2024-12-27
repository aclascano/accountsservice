package com.aldev.accountservice.repository;

import com.aldev.accountservice.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
