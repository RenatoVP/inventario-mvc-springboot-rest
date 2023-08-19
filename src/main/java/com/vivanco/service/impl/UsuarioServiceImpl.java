package com.vivanco.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vivanco.dto.request.SignInRequest;
import com.vivanco.dto.request.SignUpRequest;
import com.vivanco.entity.Rol;
import com.vivanco.entity.Usuario;
import com.vivanco.repository.RolRepository;
import com.vivanco.repository.UsuarioRepository;
import com.vivanco.security.UserDetailsImpl;
import com.vivanco.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public UserDetailsImpl authenticate(SignInRequest signInRequest) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return (UserDetailsImpl) auth.getPrincipal();
	}

	@Transactional
	@Override
	public void register(SignUpRequest signUpRequest) {
		Usuario usuario = new Usuario();
		usuario.setNombre(signUpRequest.getUsername());
		usuario.setCorreo(signUpRequest.getEmail());
		usuario.setContrasena(passwordEncoder.encode(signUpRequest.getPassword()));
		
		Rol rol = rolRepository.findByNombre("ROLE_USER").orElse(null);
		
		usuario.setRol(rol);
		
		usuarioRepository.save(usuario);
	}

	@Override
	public void logOut() {
		
	}

}
