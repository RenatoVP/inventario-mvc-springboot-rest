package com.vivanco.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivanco.dto.request.SignInRequest;
import com.vivanco.dto.request.SignUpRequest;
import com.vivanco.dto.response.JwtResponse;
import com.vivanco.repository.UsuarioRepository;
import com.vivanco.security.UserDetailsImpl;
import com.vivanco.security.jwt.JwtUtils;
import com.vivanco.service.UsuarioService;
import com.vivanco.utils.MessageResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> autenticar(@RequestBody @Valid SignInRequest loginRequest) {
		
		UserDetailsImpl userDetails = usuarioService.authenticate(loginRequest);
		
		//Obtenemos el token del jwt
		String token = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registrar(@RequestBody @Valid SignUpRequest signUpRequest) {
		
		//Validamos si el correo ingresado existe o no
		if(usuarioRepository.existsByCorreo(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("El email ya esta en uso!"));
		}
		
		/* En caso de no existir el correo, creamos al usuario con los datos ingresados
		 * el usuario obtendra por defecto el rol ROLE_USER y retomamos respuesta exitosa
		 * */
		usuarioService.register(signUpRequest);
		
		return new ResponseEntity<>(new MessageResponse("Usuario registrado exitosamente"), HttpStatus.CREATED);
	}
	
}
