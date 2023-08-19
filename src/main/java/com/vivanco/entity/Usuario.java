package com.vivanco.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 45, nullable = false)
	private String nombre;
	
	@Column(length = 150, nullable = false, unique = true)
	private String correo;
	
	@Column(length = 400, nullable = false)
	private String contrasena;
	
	@ManyToOne
	@JoinColumn(name = "idrol", nullable = false)
	private Rol rol;
}
