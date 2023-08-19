package com.vivanco.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaLecturaDTO {
	private Long id;
	private String nombre;
	private String descripcion;
	private List<String> productos;
	
	public CategoriaLecturaDTO(Long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
}
