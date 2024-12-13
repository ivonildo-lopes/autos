package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.autos.entity.SaidaVeiculo;

public interface SaidaVeiculoRepository extends JpaRepository<SaidaVeiculo, UUID> {

}
