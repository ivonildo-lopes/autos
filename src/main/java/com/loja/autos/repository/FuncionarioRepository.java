package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.autos.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

}
