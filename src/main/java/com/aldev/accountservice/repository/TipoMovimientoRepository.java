package com.aldev.accountservice.repository;

import com.aldev.accountservice.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Long> {

}
