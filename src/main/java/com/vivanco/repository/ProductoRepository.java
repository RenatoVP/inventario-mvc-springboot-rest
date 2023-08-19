package com.vivanco.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vivanco.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	Page<Producto> findAllByNombreContains(String nombre, Pageable pageable);
	
	Page<Producto> findAllByUsuarioId(Long idusuario, Pageable pageable);
	
	@Query("select p from Producto p where p.id = :idproducto and p.usuario.id = :idusuario")
	Optional<Producto> buscarPorId(@Param("idproducto") Long idproducto, @Param("idusuario") Long idusuario);
}
