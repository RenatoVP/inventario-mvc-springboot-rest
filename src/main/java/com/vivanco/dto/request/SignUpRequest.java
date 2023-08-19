package com.vivanco.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/* DTO utilizado cuando usuario se registre en la app,
 * obtendra un rol por default 
 * */
@Data @AllArgsConstructor
public class SignUpRequest {
	private String username;
	private String email;
	private String password;
}
