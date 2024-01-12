package com.dwes.api.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.api.entidades.Categoria;
import com.dwes.api.entidades.Jabon;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{
    Page<Categoria> findAll(Pageable pageable);
 }
