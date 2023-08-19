package com.vivanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivanco.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

}
