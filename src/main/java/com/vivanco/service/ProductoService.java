package com.vivanco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vivanco.dto.request.ProductoRegistroDTO;
import com.vivanco.dto.response.ProductoLecturaDTO;

public interface ProductoService {
	//Page<ProductoLecturaDTO> listar(Page<Producto> page);
	Page<ProductoLecturaDTO> listar(Pageable pageable);
	
	ProductoLecturaDTO obtener(Long idproducto);
	
	void registrar(ProductoRegistroDTO productoRegistroDTO);
	
	void actualizar(Long idproducto, ProductoRegistroDTO productoRegistroDTO);
	
	void eliminar(Long idproducto);
}
