package com.vivanco.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vivanco.dto.request.ProductoRegistroDTO;
import com.vivanco.dto.response.ProductoLecturaDTO;
import com.vivanco.entity.Categoria;
import com.vivanco.entity.Producto;
import com.vivanco.entity.Usuario;
import com.vivanco.exception.ResourceNotFoundException;
import com.vivanco.mappers.ProductoMapper;
import com.vivanco.repository.CategoriaRepository;
import com.vivanco.repository.ProductoRepository;
import com.vivanco.repository.UsuarioRepository;
import com.vivanco.service.ProductoService;
import com.vivanco.utils.AuthUtils;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	@Override
	public Page<ProductoLecturaDTO> listar(Pageable pageable) {
		Long idusuario = AuthUtils.getCurrentUserId();
		
		Page<Producto> productos = productoRepository.findAllByUsuarioId(idusuario, pageable);
		List<ProductoLecturaDTO> response = new ArrayList<>();
		
		for(Producto producto : productos) {
			ProductoLecturaDTO productoDTO = ProductoMapper.toDTO(producto);
			response.add(productoDTO);
		}
		
		return new PageImpl<>(response, productos.getPageable(), productos.getTotalElements());
	}

	@Transactional
	@Override
	public ProductoLecturaDTO obtener(Long idproducto) {
		Long idusuario = AuthUtils.getCurrentUserId();
		
		Producto producto = productoRepository.buscarPorId(idproducto, idusuario)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
		
		return ProductoMapper.toDTO(producto);
	}

	@Transactional
	@Override
	public void registrar(ProductoRegistroDTO productoRegistroDTO) {
		Long idusuario = AuthUtils.getCurrentUserId();
		
		Categoria categoria = categoriaRepository.buscarPorId(productoRegistroDTO.getIdCategoria(), idusuario)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
		
		Producto producto = ProductoMapper.toEntity(productoRegistroDTO);
		producto.setFecharegistro(LocalDateTime.now());
		producto.setCategoria(categoria);
		
		Usuario usuario = usuarioRepository.findById(idusuario).orElse(null);
		producto.setUsuario(usuario);
		
		productoRepository.save(producto);
	}

	@Transactional
	@Override
	public void actualizar(Long idproducto, ProductoRegistroDTO productoRegistroDTO) {
		Long idusuario = AuthUtils.getCurrentUserId();
		
		Producto producto = productoRepository.buscarPorId(idproducto, idusuario)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
		
		Categoria categoria = categoriaRepository.buscarPorId(productoRegistroDTO.getIdCategoria(), idusuario)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
		
		producto.setNombre(productoRegistroDTO.getNombre());
		producto.setDescripcion(productoRegistroDTO.getDescripcion());
		producto.setCosto(productoRegistroDTO.getCosto());
		producto.setPrecio(productoRegistroDTO.getPrecio());
		producto.setStock(productoRegistroDTO.getStock());
		producto.setCategoria(categoria);
        
        productoRepository.save(producto);
	}

	@Transactional
	@Override
	public void eliminar(Long idproducto) {
		Long idusuario = AuthUtils.getCurrentUserId();
		
		Producto producto = productoRepository.buscarPorId(idproducto, idusuario)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
		
		productoRepository.delete(producto);
	}

}
