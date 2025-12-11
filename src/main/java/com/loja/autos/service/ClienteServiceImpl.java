package com.loja.autos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.ClienteRequest;
import com.loja.autos.dto.request.PessoaRequest;
import com.loja.autos.dto.response.ClienteResponse;
import com.loja.autos.entity.Cliente;
import com.loja.autos.entity.Pessoa;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.mappers.ClienteMapper;
import com.loja.autos.mappers.PessoaMapper;
import com.loja.autos.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl {
	
	private final ClienteRepository repository;
	
	private final PessoaServiceImpl pessoaService;
	
	@Transactional
	public String save(ClienteRequest request) {
		
		verificaSeClienteJaExiste(request);
		
		Pessoa pessoa = this.pessoaService.getPessoa(PessoaMapper.clienteToPessoaRequest(request));

		Cliente cliente = ClienteMapper.converterToModel(request, pessoa);
		
		Cliente clienteSaved = repository.save(cliente);
		
		return clienteSaved.getId().toString() + " Cliente cadastrado com sucesso";
	}
	
	@Transactional
	public String update(ClienteRequest request, UUID id) {
		
		PessoaRequest pessoaRequest = PessoaMapper.clienteToPessoaRequest(request);

		Cliente clienteBase = findById(id);
		Pessoa pessoa = this.pessoaService.update(PessoaMapper.dtoToModel(pessoaRequest), clienteBase.getPessoa());
		
		Cliente clienteUpdate = ClienteMapper.converterToModel(request, clienteBase, pessoa);
		
		Cliente clienteUpdated = repository.save(clienteUpdate);
		
		return clienteUpdated.getId().toString() + " Cliente atualizado com sucesso";
	}
	
	@Transactional
	public void saveClientPosVenda(Cliente cliente) {
		
		cliente.setDataUltimaCompra(LocalDate.now());
		cliente.setNotificacaoAusencia1(null);
		cliente.setNotificacaoAusencia2(null);
		cliente.setAtivo(true);
		repository.save(cliente);
	}
	
	
	public Cliente findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse cliente não existe."));
	}
	
	public List<ClienteResponse> findAll() {
		List<Cliente> lista = this.repository.findAll();
		
		return lista.stream().map(c -> converterResponse(c)
				).collect(Collectors.toList());
	}

	public ClienteResponse converterResponse(Cliente c) {
		return ClienteResponse.builder()
				.nome(c.getPessoa().getNome())
				.id(c.getId())
				.ativo(c.getAtivo())
				.dataNascimento(c.getPessoa().getDataNascimento())
				.email(c.getEmail())
				.telefone(c.getTelefone())
				.build();
	}

	private void verificaSeClienteJaExiste(ClienteRequest request) {
		Cliente clienteNaBase = repository.findByDocumento(request.getDocumento());
		
		if(clienteNaBase != null) {
			throw new NegocioException("Esse cliente já esta cadastrado.");
		}
	}

}
