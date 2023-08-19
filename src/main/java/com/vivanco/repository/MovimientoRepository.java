package com.vivanco.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vivanco.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
	
	@Query(
			nativeQuery = true,
			value = "select m.* from movimientos m inner join productos p "
					+ "on m.idproducto = p.id inner join categorias c "
					+ "on p.idcategoria = c.id "
					+ "where c.idusuario = :idusuario"
	)
	Page<Movimiento> listarMovimientosDeUsuarioIngresado(@Param("idusuario") Long idusuario, Pageable pageable);
}
