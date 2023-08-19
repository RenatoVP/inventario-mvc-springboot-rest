package com.vivanco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vivanco.dto.request.MovimientoRegistroDTO;
import com.vivanco.dto.response.MovimientoLecturaDTO;

public interface MovimientoService {
	Page<MovimientoLecturaDTO> listar(Pageable pageable);
	
	void registrar(MovimientoRegistroDTO movimientoDTO);
}
