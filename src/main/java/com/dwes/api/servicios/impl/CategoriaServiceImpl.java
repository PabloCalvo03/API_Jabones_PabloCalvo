package com.dwes.api.servicios.impl;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dwes.api.entidades.Categoria;
import com.dwes.api.entidades.Ingrediente;
import com.dwes.api.entidades.Jabon;
import com.dwes.api.entidades.enumerados.TipoDePiel;
import com.dwes.api.repositorios.CategoriaRepository;
import com.dwes.api.servicios.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

	@Override
	public Page<Categoria> findAll(Pageable pageable) {
		return categoriaRepository.findAll(pageable);
	}

	@Override
	public Optional<Categoria> findById(Long id) {
		  return categoriaRepository.findById(id);
	}

	@Override
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public void deleteById(Long id) {
		categoriaRepository.deleteById(id);
		
	}

	@Override
	public boolean existsById(Long id) {
		return categoriaRepository.existsById(id);
	}

}
