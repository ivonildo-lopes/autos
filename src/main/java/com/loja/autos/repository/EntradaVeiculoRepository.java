package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.autos.entity.EntradaVeiculo;

public interface EntradaVeiculoRepository extends JpaRepository<EntradaVeiculo, UUID> {

}
