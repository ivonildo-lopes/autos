package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.autos.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	
	@Query("SELECT c FROM Cliente c WHERE c.pessoa.documento = :documento")
	Cliente findByDocumento(String documento);

}
