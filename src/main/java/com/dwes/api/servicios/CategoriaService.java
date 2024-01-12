package com.dwes.api.servicios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dwes.api.entidades.Categoria;
import com.dwes.api.entidades.Jabon;


public interface CategoriaService {
	Page<Categoria> findAll(Pageable pageable);
    Optional<Categoria> findById(Long id);
    Categoria save(Categoria jabon);
    void deleteById(Long id);
    

    // Métodos para paginación y búsqueda
	boolean existsById(Long id);
  
}
