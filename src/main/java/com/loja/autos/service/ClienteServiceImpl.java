package com.loja.autos.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.ClienteRequest;
import com.loja.autos.dto.request.PessoaRequest;
import com.loja.autos.entity.Cliente;
import com.loja.autos.entity.Pessoa;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.mappers.ClienteMapper;
import com.loja.autos.mappers.PessoaMapper;
import com.loja.autos.repository.ClienteRepository;

@Service
public class ClienteServiceImpl {
	
	private final ClienteRepository repository;
	
	private final PessoaServiceImpl pessoaService;
	
	ClienteServiceImpl(ClienteRepository repository, PessoaServiceImpl pessoaService) {
		this.repository = repository;
		this.pessoaService = pessoaService;
	}
	
	@Transactional
	public Cliente save(ClienteRequest request) {
		
		verificaSeClienteJaExiste(request);
		
		Pessoa pessoa = this.pessoaService.getPessoa(PessoaMapper.clienteToPessoaRequest(request));

		Cliente cliente = ClienteMapper.converterToModel(request, pessoa);
		
		return repository.save(cliente);
	}
	
	@Transactional
	public Cliente update(ClienteRequest request, UUID id) {
		
		PessoaRequest pessoaRequest = PessoaMapper.clienteToPessoaRequest(request);

		Cliente clienteBase = findById(id);
		Pessoa pessoa = this.pessoaService.update(PessoaMapper.dtoToModel(pessoaRequest), clienteBase.getPessoa());
		
		Cliente clienteUpdate = ClienteMapper.converterToModel(request, clienteBase, pessoa);
		
		return repository.save(clienteUpdate);
	}
	
	
	public Cliente findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse cliente não existe."));
	}

	private void verificaSeClienteJaExiste(ClienteRequest request) {
		Cliente clienteNaBase = repository.findByDocumento(request.getDocumento());
		
		if(clienteNaBase != null) {
			throw new NegocioException("Esse cliente já esta cadastrado.");
		}
	}

}
