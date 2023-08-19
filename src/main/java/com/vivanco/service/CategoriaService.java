package com.vivanco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vivanco.dto.request.CategoriaRegistroDTO;
import com.vivanco.dto.response.CategoriaLecturaDTO;

public interface CategoriaService {
	Page<CategoriaLecturaDTO> listar(Pageable pageable);
	
	CategoriaLecturaDTO obtener(Long idcategoria);
	
	void registrar(CategoriaRegistroDTO categoria);
	
	void actualizar(Long idcategoria, CategoriaRegistroDTO categoria);
	
	void eliminar(Long idcategoria);
}
