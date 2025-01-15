package com.loja.autos.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.SaidaVeiculoRequest;
import com.loja.autos.dto.request.VeiculoRequest;
import com.loja.autos.entity.EntradaVeiculo;
import com.loja.autos.entity.SaidaVeiculo;
import com.loja.autos.entity.Veiculo;
import com.loja.autos.enums.StatusVeiculo;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.mappers.SaidaVeiculoMapper;
import com.loja.autos.repository.SaidaVeiculoRepository;

@Service
public class SaidaVeiculoServiceImpl {

	private final SaidaVeiculoRepository repository;
	
	private final VeiculoServiceImpl veiculoService;
	
	private final EntradaVeiculoServiceImpl entradaService;
	
	private final ClienteServiceImpl clienteService;

	SaidaVeiculoServiceImpl(SaidaVeiculoRepository repository, VeiculoServiceImpl veiculoService, 
			EntradaVeiculoServiceImpl entradaService, ClienteServiceImpl clienteService) {
		this.repository = repository;
		this.veiculoService = veiculoService;
		this.entradaService = entradaService;
		this.clienteService = clienteService;
	}

	@Transactional
	public SaidaVeiculo register(SaidaVeiculoRequest request) {
		
		var entradaVeiculo = entradaService.findById(request.getIdEntradaVeiculo());
		var cliente = clienteService.findById(request.getIdCliente());
		
		validacaoSaidaVeiculo(entradaVeiculo);
		
		SaidaVeiculo sv = SaidaVeiculoMapper.converterToModel(request ,cliente, entradaVeiculo);
		
		SaidaVeiculo entradaEmLoja = repository.save(sv);
		
		updateStatus(entradaVeiculo);
		
		return entradaEmLoja;
	}

	private void updateStatus(EntradaVeiculo entradaVeiculo) {
		veiculoService.updateStatus(entradaVeiculo.getVeiculo(), StatusVeiculo.VENDIDO);
		entradaService.updateStatusEntradaVeiculo(entradaVeiculo, StatusVeiculo.VENDIDO);
	}

	private void validacaoSaidaVeiculo(EntradaVeiculo entradaVeiculo) {
		if(!StatusVeiculo.EM_LOJA.equals(entradaVeiculo.getStatusVeiculo())) {
			throw new NegocioException("Esse veiculo não pode ser dado a saida.");
		}
	}

	@Transactional
	public Veiculo update(VeiculoRequest request, UUID id) {
		return null;
	}

	public SaidaVeiculo findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse veiculo não foi dado saida."));
	}

}
