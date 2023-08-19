package com.vivanco.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoRegistroDTO {
	
	@NotNull(message = "El id de producto no puede ser nulo.")
	private Long idProducto;
	
	@Min(value = 1, message = "La cantidad minima es de 1")
	@Max(value = 99999, message = "La cantidad maxima es de 99999")
	private int cantidad;
	
	@NotNull(message = "El tipo de movimiento no puede ser nulo.")
	private String tipoMovimiento;
}
