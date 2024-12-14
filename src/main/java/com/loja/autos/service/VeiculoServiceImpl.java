package com.loja.autos.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.VeiculoRequest;
import com.loja.autos.entity.Veiculo;
import com.loja.autos.enums.StatusVeiculo;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.mappers.VeiculoMapper;
import com.loja.autos.repository.VeiculoRepository;

@Service
public class VeiculoServiceImpl {
	
	private final VeiculoRepository repository;
	
	VeiculoServiceImpl(VeiculoRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public Veiculo register(VeiculoRequest request) {
		
		verificaSeVeiculoJaExiste(request);
		var veiculo = VeiculoMapper.conververToModel(request);
		veiculo.setStatusVeiculo(StatusVeiculo.PRONTO_PARA_ENTRADA);
		return repository.save(veiculo);
	}
	
	@Transactional
	public Veiculo update(VeiculoRequest request, UUID id) {

		var veiculoBase = findById(id);
		
		var veiculo = VeiculoMapper.conververToModel(request, veiculoBase);
		
		return repository.save(veiculo);
	}
	
	public Veiculo updateStatus(Veiculo veiculo, StatusVeiculo status) {
		veiculo.setStatusVeiculo(status);
		return repository.save(veiculo);
	}
	
	
	public Veiculo findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse veiculo não existe."));
	}
	
	public Veiculo findByPlaca(String placa) {
		return this.repository.findByPlaca(placa).orElseThrow(() -> new NegocioException("Esse veiculo não existe."));
	}

	private void verificaSeVeiculoJaExiste(VeiculoRequest request) {
		var veiculo = repository.findByPlaca(request.getPlaca());
		
		if(veiculo.isPresent()) {
			throw new NegocioException("Esse veiculo já esta cadastrado.");
		}
	}

}
