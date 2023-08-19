package com.vivanco.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.vivanco.dto.request.CategoriaRegistroDTO;
import com.vivanco.dto.response.CategoriaLecturaDTO;
import com.vivanco.entity.Categoria;

public class CategoriaMapper {
	
	public static CategoriaLecturaDTO toDTO(Categoria categoria) {
		CategoriaLecturaDTO categoriaLectura = new CategoriaLecturaDTO(
				categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
		
		//Lista de nombre de los productos pertenecientes a la categoria
		List<String> productos = categoria.getProductos().stream()
				.map(producto -> producto.getNombre())
				.collect(Collectors.toList());
		
		categoriaLectura.setProductos(productos);
		
		return categoriaLectura;
	}
	
	public static Categoria toEntity(CategoriaRegistroDTO registro) {
		Categoria categoria = new Categoria();
		categoria.setNombre(registro.getNombre());
		categoria.setDescripcion(registro.getDescripcion());
		
		return categoria;
	}
}
