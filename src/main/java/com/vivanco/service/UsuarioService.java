package com.vivanco.service;

import com.vivanco.dto.request.SignInRequest;
import com.vivanco.dto.request.SignUpRequest;
import com.vivanco.security.UserDetailsImpl;

public interface UsuarioService {
	UserDetailsImpl authenticate(SignInRequest signInRequest);
	
	void register(SignUpRequest signUpRequest);
	
	void logOut();
}
