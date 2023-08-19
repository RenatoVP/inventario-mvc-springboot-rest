package com.vivanco.mappers;

import com.vivanco.dto.request.MovimientoRegistroDTO;
import com.vivanco.dto.response.MovimientoLecturaDTO;
import com.vivanco.entity.Movimiento;

public class MovimientoMapper {
	
	public static MovimientoLecturaDTO toDTO(Movimiento movimiento) {
		MovimientoLecturaDTO lectura = new MovimientoLecturaDTO(
				movimiento.getId(), 
				movimiento.getProducto().getNombre(), 
				movimiento.getCantidad(), 
				movimiento.getTipoMovimiento()
		);
		
		return lectura;
	}
	
	public static Movimiento toEntity(MovimientoRegistroDTO registro) {
		Movimiento movimiento = new Movimiento();
		movimiento.setCantidad(registro.getCantidad());
		movimiento.setTipoMovimiento(registro.getTipoMovimiento());
		
		return movimiento;
	}
}
