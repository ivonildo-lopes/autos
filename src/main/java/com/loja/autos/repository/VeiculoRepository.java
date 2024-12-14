package com.loja.autos.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.autos.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

	Optional<Veiculo> findByPlaca(String placa);

}
