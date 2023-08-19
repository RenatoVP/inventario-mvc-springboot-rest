package com.vivanco.mappers;

import com.vivanco.dto.request.ProductoRegistroDTO;
import com.vivanco.dto.response.ProductoLecturaDTO;
import com.vivanco.entity.Producto;

public class ProductoMapper {

	public static ProductoLecturaDTO toDTO(Producto producto) {
		ProductoLecturaDTO productoLectura = new ProductoLecturaDTO(
				producto.getId(), 
				producto.getNombre(), 
				producto.getDescripcion(), 
				producto.getCosto(),
				producto.getPrecio(), 
				producto.getStock(), 
				producto.getFecharegistro()
		);
		
		String categoria = (producto.getCategoria() == null) ? null : producto.getCategoria().getNombre();
		
		productoLectura.setCategoria(categoria);
		
		return productoLectura;
	}
	
	public static Producto toEntity(ProductoRegistroDTO registro) {
		Producto producto = new Producto();
		producto.setNombre(registro.getNombre());
		producto.setDescripcion(registro.getDescripcion());
		producto.setDescripcion(registro.getDescripcion());
		producto.setCosto(registro.getCosto());
		producto.setPrecio(registro.getPrecio());
		producto.setStock(registro.getStock());
		
		return producto;
	}
}
