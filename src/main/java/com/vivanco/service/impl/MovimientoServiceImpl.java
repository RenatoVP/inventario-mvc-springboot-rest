package com.vivanco.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vivanco.dto.request.MovimientoRegistroDTO;
import com.vivanco.dto.response.MovimientoLecturaDTO;
import com.vivanco.entity.Movimiento;
import com.vivanco.entity.Producto;
import com.vivanco.exception.ResourceNotFoundException;
import com.vivanco.mappers.MovimientoMapper;
import com.vivanco.repository.MovimientoRepository;
import com.vivanco.repository.ProductoRepository;
import com.vivanco.service.MovimientoService;
import com.vivanco.utils.AuthUtils;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	private MovimientoRepository movimientoRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Page<MovimientoLecturaDTO> listar(Pageable pageable) {
		//Obtenemos el usuario logeado
		Long idusuario = AuthUtils.getCurrentUserId();
		
		//Listamos los movimientos realizados por el usuario que ingreso al sistema
		Page<Movimiento> movimientos = movimientoRepository.listarMovimientosDeUsuarioIngresado(idusuario, pageable);
		List<MovimientoLecturaDTO> respuesta = new ArrayList<>();

		for (Movimiento movimiento : movimientos) {
			MovimientoLecturaDTO lectura = MovimientoMapper.toDTO(movimiento);
			respuesta.add(lectura);
		}

		return new PageImpl<>(respuesta, pageable, movimientos.getTotalElements());
	}

	@Transactional
	@Override
	public void registrar(MovimientoRegistroDTO movimientoDTO) {
		//Obtenemos el usuario logeado
		Long idusuario = AuthUtils.getCurrentUserId();
		
		//Realiza busqueda por id entre los productos registrados por usuario ingresado
		Producto producto = productoRepository.buscarPorId(idusuario, movimientoDTO.getIdProducto())
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
		
		int cantidad = movimientoDTO.getCantidad();
		int operacion;

		// En caso de que tipo movimiento sea igual a "Salida", validar que la cantidad
		// no sea superior al stock del producto
		if(movimientoDTO.getTipoMovimiento().equals("Salida")) {
			
			if(cantidad > producto.getStock()) {
				throw new ResourceNotFoundException("La cantidad ingresada supera al stock del producto");
			} else {
				//Reducir stock del producto
				operacion = producto.getStock() - cantidad;
				producto.setStock(operacion);
				
				productoRepository.save(producto);
			}
		} 
		else if(movimientoDTO.getTipoMovimiento().equals("Entrada")) {
			//Agregar stock del producto
			operacion = producto.getStock() + cantidad;
			producto.setStock(operacion);
			
			productoRepository.save(producto);
		} 
		else {
			throw new ResourceNotFoundException("El valor tipo de movimiento solo acepta valores como 'Salida' y 'Entrada'");
		}
		
		//Agregar movimiento de salida realizada
		Movimiento movimiento = MovimientoMapper.toEntity(movimientoDTO);
		movimiento.setProducto(producto);

		movimientoRepository.save(movimiento);
	}

}
