package com.vivanco.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vivanco.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	Page<Producto> findAllByNombreContains(String nombre, Pageable pageable);
}
