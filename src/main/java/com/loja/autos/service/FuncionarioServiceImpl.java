package com.loja.autos.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.FuncionarioRequest;
import com.loja.autos.entity.Funcionario;
import com.loja.autos.entity.Pessoa;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.mappers.PessoaMapper;
import com.loja.autos.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl {
	
	private final FuncionarioRepository repository;
	
	private final PessoaServiceImpl pessoaService;
	
	FuncionarioServiceImpl(FuncionarioRepository repository, PessoaServiceImpl pessoaService) {
		this.repository = repository;
		this.pessoaService = pessoaService;
	}
	
	@Transactional
	public Funcionario save(FuncionarioRequest request) {
		
		verificaSeFuncionarioJaExiste(request);
		
		Pessoa pessoa = this.pessoaService.getPessoa(PessoaMapper.funcionarioToPessoaRequest(request));

		Funcionario funcionario = new Funcionario();
		funcionario.setEmail(request.getEmail());
		funcionario.setCargo(request.getCargo());
		funcionario.setDataAdmissao(request.getDataAdmissao());
		funcionario.setTelefone(request.getTelefone());
		funcionario.setPessoa(pessoa);
		
		return repository.save(funcionario);
	}
	
	@Transactional
	public Funcionario update(FuncionarioRequest request, UUID id) {
		
		Funcionario funcionario = findById(id);
		
		funcionario.setEmail(request.getEmail());
		funcionario.setCargo(request.getCargo());
		funcionario.setDataAdmissao(request.getDataAdmissao());
		funcionario.setTelefone(request.getTelefone());
		
		return repository.save(funcionario);
	}
	
	
	public Funcionario findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse funcionario não existe."));
	}
	
	public List<Funcionario> findAll() {
		return this.repository.findAll();
	}

	private void verificaSeFuncionarioJaExiste(FuncionarioRequest request) {
		Funcionario clienteNaBase = repository.findByDocumento(request.getDocumento());
		
		if(clienteNaBase != null) {
			throw new NegocioException("Esse funcionario já esta cadastrado.");
		}
	}

}
