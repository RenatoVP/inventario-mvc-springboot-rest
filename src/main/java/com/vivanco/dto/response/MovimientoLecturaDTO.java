package com.vivanco.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoLecturaDTO {
	private Long id;
	private String producto;
	private int cantidad;
	private String tipoMovimiento;
}
