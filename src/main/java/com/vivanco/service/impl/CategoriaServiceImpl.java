package com.vivanco.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vivanco.dto.request.CategoriaRegistroDTO;
import com.vivanco.dto.response.CategoriaLecturaDTO;
import com.vivanco.entity.Categoria;
import com.vivanco.entity.Producto;
import com.vivanco.entity.Usuario;
import com.vivanco.exception.ResourceNotFoundException;
import com.vivanco.mappers.CategoriaMapper;
import com.vivanco.repository.CategoriaRepository;
import com.vivanco.repository.ProductoRepository;
import com.vivanco.repository.UsuarioRepository;
import com.vivanco.service.CategoriaService;
import com.vivanco.utils.AuthUtils;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	@Override
	public Page<CategoriaLecturaDTO> listar(Pageable pageable) {
		//Obtener al usuario autenticado, sera utilizado en los metodos
		Long userId = AuthUtils.getCurrentUserId();
		
		Page<Categoria> categorias = categoriaRepository.findAllByUsuarioId(userId, pageable);
		List<CategoriaLecturaDTO> response = new ArrayList<>();
		
		for(Categoria categoria : categorias) {
			CategoriaLecturaDTO categoriaDTO = CategoriaMapper.toDTO(categoria);
			response.add(categoriaDTO);
		}
		
		return new PageImpl<>(response, categorias.getPageable(), categorias.getTotalElements());
	}

	@Transactional
	@Override
	public CategoriaLecturaDTO obtener(Long idcategoria) {
		//Obtener al usuario autenticado, sera utilizado en los metodos
		Long userId = AuthUtils.getCurrentUserId();
		
		Categoria categoria = categoriaRepository.buscarPorId(idcategoria, userId)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
		
		return CategoriaMapper.toDTO(categoria);
	}

	@Transactional
	@Override
	public void registrar(CategoriaRegistroDTO categoria) {
		//Obtener al usuario autenticado, sera utilizado en los metodos
		Long userId = AuthUtils.getCurrentUserId();
		
		//Mapear de dto a entity, asignando ademas al usuario que registra la categoria
		Categoria nuevaCategoria = CategoriaMapper.toEntity(categoria);
		Usuario usuario = usuarioRepository.findById(userId).orElse(null);
		
		nuevaCategoria.setUsuario(usuario);
		categoriaRepository.save(nuevaCategoria);
	}

	@Transactional
	@Override
	public void actualizar(Long idcategoria, CategoriaRegistroDTO categoria) {
		//Obtener al usuario autenticado, sera utilizado en los metodos
		Long userId = AuthUtils.getCurrentUserId();
				
		Categoria categoriaEncontrada = categoriaRepository.buscarPorId(idcategoria, userId)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
		
		categoriaEncontrada.setNombre(categoria.getNombre());
		categoriaEncontrada.setDescripcion(categoria.getDescripcion());
		
		categoriaRepository.save(categoriaEncontrada);
	}

	@Transactional
	@Override
	public void eliminar(Long idcategoria) {
		//Obtener al usuario autenticado, sera utilizado en los metodos
		Long userId = AuthUtils.getCurrentUserId();
		
		Categoria categoria = categoriaRepository.buscarPorId(idcategoria, userId)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
		
		//Si la categoria a eliminar tiene productos, setear producto.setCategoria a null
		if(!categoria.getProductos().isEmpty()) {
			for(Producto producto : categoria.getProductos()) {
				producto.setCategoria(null);
				productoRepository.save(producto);
			}
		}
		
		categoriaRepository.delete(categoria);
	}

}
