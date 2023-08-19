package com.vivanco.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivanco.dto.request.ProductoRegistroDTO;
import com.vivanco.dto.response.ProductoLecturaDTO;
import com.vivanco.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	//Listara todas las categorias del usuario logeado
	@GetMapping
	public ResponseEntity<Page<ProductoLecturaDTO>> listarProductosDeUsuario(
			@PageableDefault(page = 0, size = 10) Pageable pageable
	) {
		return ResponseEntity.ok(productoService.listar(pageable));
	}
	
	@GetMapping("/{idproducto}")
	public ResponseEntity<ProductoLecturaDTO> obtener(@PathVariable Long idproducto) {
		return ResponseEntity.ok(productoService.obtener(idproducto));
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@RequestBody @Valid ProductoRegistroDTO producto) {
		productoService.registrar(producto);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{idproducto}")
	public ResponseEntity<Void> actualizar(
			@PathVariable Long idproducto,
			@RequestBody @Valid ProductoRegistroDTO producto
	) {
		productoService.actualizar(idproducto, producto);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{idproducto}")
	public ResponseEntity<Void> eliminar(@PathVariable Long idproducto) {
		productoService.eliminar(idproducto);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
