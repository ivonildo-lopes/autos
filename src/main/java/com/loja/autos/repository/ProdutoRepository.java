package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.autos.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

}
