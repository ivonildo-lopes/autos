package com.loja.autos.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.SaidaVeiculoRequest;
import com.loja.autos.dto.request.VeiculoRequest;
import com.loja.autos.entity.SaidaVeiculo;
import com.loja.autos.entity.Veiculo;
import com.loja.autos.enums.StatusVeiculo;
import com.loja.autos.exceptions.NegocioException;
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
		
		if(!StatusVeiculo.EM_LOJA.equals(entradaVeiculo.getStatusVeiculo())) {
			throw new NegocioException("Esse veiculo não pode ser dado a saida.");
		}
		
		SaidaVeiculo sv = new SaidaVeiculo();
		sv.setCliente(cliente);
		sv.setEntradaVeiculo(entradaVeiculo);
		sv.setDataSaida(request.getDataSaida());
		sv.setValorSaida(request.getValorSaida());

		SaidaVeiculo entradaEmLoja = repository.save(sv);
		
		veiculoService.updateStatus(entradaVeiculo.getVeiculo(), StatusVeiculo.VENDIDO);
		entradaService.updateStatusEntradaVeiculo(entradaVeiculo, StatusVeiculo.VENDIDO);
		
		return entradaEmLoja;
	}

	@Transactional
	public Veiculo update(VeiculoRequest request, UUID id) {
		return null;
	}

	public SaidaVeiculo findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse veiculo não foi dado saida."));
	}

}
