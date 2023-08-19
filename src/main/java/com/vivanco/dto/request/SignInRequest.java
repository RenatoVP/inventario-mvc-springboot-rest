package com.vivanco.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SignInRequest {
	
	@NotBlank(message = "El campo username no puede estar vacio.")
	private String username;
	
	@NotBlank(message = "El campo contraseña no puede estar vacio.")
	private String password;
}
