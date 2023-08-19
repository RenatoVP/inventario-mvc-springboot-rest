package com.vivanco.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRegistroDTO {
	@NotNull(message = "El nombre no puede estar vacio")
	@Size(min = 3, max = 150, message = "El nombre debe contener entre 3 a 150 caracteres")
	private String nombre;

	@Size(max = 400, message = "La descripcion debe contener como maximo 400 caracteres")
	private String descripcion;

	@Min(value = 1, message = "El costo minimo de un producto es de 1")
	@Max(value = 99999, message = "El costo maximo de un producto es de 99999")
	private double costo;

	@Min(value = 1, message = "El precio minimo de un producto es de 1")
	@Max(value = 99999, message = "El precio maximo de un producto es de 99999")
	private double precio;

	@Min(value = 1, message = "El stock minimo de un producto es de 1")
	@Max(value = 99999, message = "El stock maximo de un producto es de 99999")
	private int stock;

	@NotNull(message = "La categoría es obligatoria")
	private Long idCategoria;

}
