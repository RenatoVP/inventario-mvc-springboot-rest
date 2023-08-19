package com.vivanco.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoLecturaDTO {
	private Long id;
	private String nombre;
	private String descripcion;
	private double costo;
	private double precio;
	private int stock;
	private LocalDateTime fecharegistro;
	private String categoria;

	public ProductoLecturaDTO(Long id, String nombre, String descripcion, double costo, double precio, int stock,
			LocalDateTime fecharegistro) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.precio = precio;
		this.stock = stock;
		this.fecharegistro = fecharegistro;
	}

}
