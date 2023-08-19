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

import com.vivanco.dto.request.CategoriaRegistroDTO;
import com.vivanco.dto.response.CategoriaLecturaDTO;
import com.vivanco.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	//Listara todas las categorias del usuario logeado
	@GetMapping
	public ResponseEntity<Page<CategoriaLecturaDTO>> listarCategoriasDeUsuario(
			@PageableDefault(page = 0, size = 10) Pageable pageable
	) {
		return ResponseEntity.ok(categoriaService.listar(pageable));
	}
	
	@GetMapping("/{idcategoria}")
	public ResponseEntity<CategoriaLecturaDTO> obtener(@PathVariable Long idcategoria) {
		return ResponseEntity.ok(categoriaService.obtener(idcategoria));
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@RequestBody @Valid CategoriaRegistroDTO categoria) {
		categoriaService.registrar(categoria);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{idcategoria}")
	public ResponseEntity<Void> actualizar(
			@PathVariable Long idcategoria,
			@RequestBody @Valid CategoriaRegistroDTO categoria
	) {
		categoriaService.actualizar(idcategoria, categoria);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{idcategoria}")
	public ResponseEntity<Void> eliminar(@PathVariable Long idcategoria) {
		categoriaService.eliminar(idcategoria);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
