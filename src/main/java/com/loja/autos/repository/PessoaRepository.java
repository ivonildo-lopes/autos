package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.autos.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
	
	Pessoa findByDocumento(String documento);

}
