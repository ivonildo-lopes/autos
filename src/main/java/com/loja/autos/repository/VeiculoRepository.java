package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.autos.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

}
