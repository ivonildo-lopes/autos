package com.loja.autos.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.EntradaVeiculoRequest;
import com.loja.autos.entity.EntradaVeiculo;
import com.loja.autos.enums.StatusVeiculo;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.mappers.EntradaVeiculoMapper;
import com.loja.autos.repository.EntradaVeiculoRepository;

@Service
public class EntradaVeiculoServiceImpl {

	private final EntradaVeiculoRepository repository;
	
	private final VeiculoServiceImpl veiculoService;

	EntradaVeiculoServiceImpl(EntradaVeiculoRepository repository, VeiculoServiceImpl veiculoService) {
		this.repository = repository;
		this.veiculoService = veiculoService;
	}

	@Transactional
	public EntradaVeiculo register(EntradaVeiculoRequest request) {
		
		var veiculo = veiculoService.findById(request.getIdVeiculo());
		
		if(!StatusVeiculo.PRONTO_PARA_ENTRADA.equals(veiculo.getStatusVeiculo())) {
			throw new NegocioException("Esse veiculo não pode ser dado entrada.");
		}
		
		EntradaVeiculo ev = EntradaVeiculoMapper.converterToModel(request);
		ev.setVeiculo(veiculo);
		ev.setStatusVeiculo(StatusVeiculo.EM_LOJA);

		EntradaVeiculo entradaEmLoja = repository.save(ev);
		
		veiculoService.updateStatus(veiculo, StatusVeiculo.EM_LOJA);
		
		return entradaEmLoja;
	}

	public EntradaVeiculo updateStatusEntradaVeiculo(EntradaVeiculo entradaVeiculo, StatusVeiculo status) {
		entradaVeiculo.setStatusVeiculo(status);
		return repository.save(entradaVeiculo);
	}

	public EntradaVeiculo findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse veiculo não foi dado entrada."));
	}

}
