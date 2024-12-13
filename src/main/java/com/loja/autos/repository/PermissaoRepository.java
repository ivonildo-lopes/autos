package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.autos.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, UUID> {

}
