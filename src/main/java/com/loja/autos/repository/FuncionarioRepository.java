package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.autos.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

	@Query("SELECT f FROM Funcionario f WHERE f.pessoa.documento = :documento")
	Funcionario findByDocumento(String documento);

}
