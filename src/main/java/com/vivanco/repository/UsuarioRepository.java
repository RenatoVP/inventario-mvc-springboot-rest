package com.vivanco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivanco.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByCorreoOrNombre(String correo, String nombre);
	Boolean existsByCorreo(String correo);
}
