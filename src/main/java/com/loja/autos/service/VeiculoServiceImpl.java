package com.loja.autos.service;

import java.util.UUID;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.controllers.VeiculoController;
import com.loja.autos.dto.request.VeiculoRequest;
import com.loja.autos.dto.response.VeiculoResponse;
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
	public VeiculoResponse register(VeiculoRequest request) {
		
		verificaSeVeiculoJaExiste(request);
		var veiculo = VeiculoMapper.conververToModel(request);
		veiculo.setStatusVeiculo(StatusVeiculo.PRONTO_PARA_ENTRADA);
		
		var response = VeiculoMapper.conververToResponse(repository.save(veiculo));
		
		adicionarLinksHateoas(response, response.getId());
		return response;
	}
	
	@Transactional
	public VeiculoResponse update(VeiculoRequest request, UUID id) {

		var veiculoBase = findById(id);
		
		var veiculo = VeiculoMapper.conververToModel(request, veiculoBase);
		
		var response = VeiculoMapper.conververToResponse(repository.save(veiculo));
		
		adicionarLinksHateoas(response, response.getId());
		
		return response;
	}
	
	public Veiculo updateStatus(Veiculo veiculo, StatusVeiculo status) {
		veiculo.setStatusVeiculo(status);
		return repository.save(veiculo);
	}
	
	
	public Veiculo findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse veiculo não existe."));
	}
	
	public VeiculoResponse findByPlaca(String placa) {
		var veiculo = this.repository.findByPlaca(placa).orElseThrow(() -> new NegocioException("Esse veiculo não existe."));
		
		var response = VeiculoMapper.conververToResponse(veiculo);
		
		adicionarLinksHateoas(response, response.getId());
		return response;
	}

	private void verificaSeVeiculoJaExiste(VeiculoRequest request) {
		var veiculo = repository.findByPlaca(request.getPlaca());
		
		if(veiculo.isPresent()) {
			throw new NegocioException("Esse veiculo já esta cadastrado.");
		}
	}
	
	private void adicionarLinksHateoas(VeiculoResponse response, UUID id) {
	    // Link para o próprio recurso (self)
	    Link selfLink = WebMvcLinkBuilder.linkTo(
	            WebMvcLinkBuilder.methodOn(VeiculoController.class)
	                    .findByPlaca(response.getPlaca()))
	            .withSelfRel();
	    
	    // Link para atualização do recurso
	    Link updateLink = WebMvcLinkBuilder.linkTo(
	            WebMvcLinkBuilder.methodOn(VeiculoController.class)
	                    .update(id, null))
	            .withRel("update");
	    
	    // Adiciona todos os links ao response
	    response.add(selfLink, updateLink);
	}

}
