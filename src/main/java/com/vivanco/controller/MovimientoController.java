package com.vivanco.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vivanco.dto.request.MovimientoRegistroDTO;
import com.vivanco.dto.response.MovimientoLecturaDTO;
import com.vivanco.service.MovimientoService;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin("*")
public class MovimientoController {
	
	@Autowired
	private MovimientoService movimientoService;
	
	@GetMapping
	public ResponseEntity<Page<MovimientoLecturaDTO>> listarMovimientosDeUsuario(
			@PageableDefault(page = 0, size = 10) Pageable pageable
	) {
		return ResponseEntity.ok(movimientoService.listar(pageable));
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@RequestBody @Valid MovimientoRegistroDTO movimiento) {
		movimientoService.registrar(movimiento);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
