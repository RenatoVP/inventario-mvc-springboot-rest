package com.vivanco.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 150, nullable = false)
	private String nombre;
	
	@Column(length = 400)
	private String descripcion;
	
	private double costo;
	
	@Column
	private double precio;
	
	@Column
	private int stock;
	
	@Column
	private LocalDateTime fecharegistro;
	
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "idcategoria")
	private Categoria categoria;
	
}
