package com.loja.autos.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.FuncionarioRequest;
import com.loja.autos.dto.response.FuncionarioResponse;
import com.loja.autos.entity.Funcionario;
import com.loja.autos.entity.Pessoa;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.mappers.PessoaMapper;
import com.loja.autos.repository.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl {
	
	private final FuncionarioRepository repository;
	
	private final PessoaServiceImpl pessoaService;
	
	@Transactional
	public String save(FuncionarioRequest request) {
		
		verificaSeFuncionarioJaExiste(request);
		
		Pessoa pessoa = this.pessoaService.getPessoa(PessoaMapper.funcionarioToPessoaRequest(request));

		Funcionario funcionario = new Funcionario();
		funcionario.setEmail(request.getEmail());
		funcionario.setCargo(request.getCargo());
		funcionario.setDataAdmissao(request.getDataAdmissao());
		funcionario.setTelefone(request.getTelefone());
		funcionario.setPessoa(pessoa);
		
		Funcionario funcionarioSaved = repository.save(funcionario);
		
		return funcionarioSaved.getId().toString() + " funcionario cadastrado com sucesso";
	}
	
	@Transactional
	public String update(FuncionarioRequest request, UUID id) {
		
		Funcionario funcionario = findById(id);
		
		funcionario.setEmail(request.getEmail());
		funcionario.setCargo(request.getCargo());
		funcionario.setDataAdmissao(request.getDataAdmissao());
		funcionario.setTelefone(request.getTelefone());
		
		Funcionario funcionarioSaved = repository.save(funcionario);
		return funcionarioSaved.getId().toString() + " funcionario atualizado com sucesso";
	}
	
	
	public Funcionario findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse funcionario não existe."));
	}
	
	public List<FuncionarioResponse> findAll() {
		return this.repository.findAll().stream().map(f -> FuncionarioResponse.builder()
				.id(f.getId())
				.nome(f.getPessoa().getNome())
				.ativo(f.isAtivo())
				.dataAdmissao(f.getDataAdmissao())
				.telefone(f.getTelefone())
				.email(f.getEmail())
				.build()
				).collect(Collectors.toList());
	}

	private void verificaSeFuncionarioJaExiste(FuncionarioRequest request) {
		Funcionario clienteNaBase = repository.findByDocumento(request.getDocumento());
		
		if(clienteNaBase != null) {
			throw new NegocioException("Esse funcionario já esta cadastrado.");
		}
	}

}
