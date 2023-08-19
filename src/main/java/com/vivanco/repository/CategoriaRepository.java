package com.vivanco.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vivanco.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Page<Categoria> findAllByUsuarioId(Long idusuario, Pageable pageable);
	
	@Query("select c from Categoria c where c.id = :idcategoria and c.usuario.id = :idusuario")
	Optional<Categoria> buscarPorId(@Param("idcategoria") Long idcategoria, @Param("idusuario") Long idusuario);
}
