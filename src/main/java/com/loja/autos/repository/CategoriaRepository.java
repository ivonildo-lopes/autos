package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.autos.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

}
