package com.vivanco.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRegistroDTO {
	
	@NotNull(message = "El nombre no puede estar vacio")
	@Size(min = 3, max = 150, message = "El nombre debe contener entre 3 a 150 caracteres")
	private String nombre;
	
	@Size(max = 400, message = "La descripcion debe contener como maximo 400 caracteres")
	private String descripcion;
}
