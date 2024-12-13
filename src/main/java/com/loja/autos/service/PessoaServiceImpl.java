package com.loja.autos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.loja.autos.dto.request.PessoaRequest;
import com.loja.autos.entity.Endereco;
import com.loja.autos.entity.Pessoa;
import com.loja.autos.mappers.PessoaMapper;
import com.loja.autos.repository.PessoaRepository;

@Service
public class PessoaServiceImpl {
	
	private final PessoaRepository repository;
	
	PessoaServiceImpl(PessoaRepository repository) {
		this.repository = repository;
	}
	
	public Pessoa getPessoa(PessoaRequest requestDto) {
		Pessoa pessoaCadastrada = findByDocumento(requestDto);
		
		Pessoa pessoa = PessoaMapper.dtoToModel(requestDto);
		
		return pessoaCadastrada == null ?
				this.save(pessoa) : 
				this.update(pessoa, pessoaCadastrada);
	}

	private Pessoa findByDocumento(PessoaRequest request) {
		return repository.findByDocumento(request.getDocumento());
	}
	
	public Pessoa save(Pessoa request) {
		final Pessoa pessoa = new Pessoa();
		
		BeanUtils.copyProperties(request, pessoa, "id", "enderecos");
		pessoa.setEnderecos(getEndereco(request, pessoa));
		
		return this.repository.save(pessoa);
	}
	
	public Pessoa update(Pessoa request, Pessoa pessoaBase) {
		
		BeanUtils.copyProperties(request, pessoaBase, "id", "enderecos");
		
        atualizarEnderecos(pessoaBase, request.getEnderecos());
		
		return this.repository.save(pessoaBase);
	}

	private List<Endereco> getEndereco(Pessoa request, final Pessoa pessoa) {
		List<Endereco> enderecos = request.getEnderecos().stream().map(er -> {
            Endereco endereco = new Endereco();
            BeanUtils.copyProperties(er, endereco);
            endereco.setPessoa(pessoa);
            return endereco;
        }).collect(Collectors.toList());
		return enderecos;
	}

	private void atualizarEnderecos(Pessoa pessoa, List<Endereco> enderecosNovos) {
		Map<UUID, Endereco> enderecosExistentesMap = pessoa.getEnderecos().stream()
				.collect(Collectors.toMap(Endereco::getId, endereco -> endereco));

		List<Endereco> enderecosAtualizados = new ArrayList<>();
		List<Endereco> enderecosParaExcluir = new ArrayList<>();

		for (Endereco enderecoNovo : enderecosNovos) {
			if (enderecoNovo.getId() != null) {
				// Atualiza endereço existente
				Endereco enderecoExistente = enderecosExistentesMap.get(enderecoNovo.getId());
				if (enderecoExistente != null) {
					BeanUtils.copyProperties(enderecoNovo, enderecoExistente, "id", "pessoa");
					enderecosAtualizados.add(enderecoExistente);
					enderecosExistentesMap.remove(enderecoNovo.getId()); // Remove do mapa para não ser excluído
				}
			} else {
				enderecoNovo.setPessoa(pessoa);
				enderecosAtualizados.add(enderecoNovo);
			}
		}

		enderecosParaExcluir.addAll(enderecosExistentesMap.values());

		// Os endereços restantes no mapa são aqueles que não foram enviados na
		// requisição e devem ser excluídos
		pessoa.getEnderecos().clear(); // Limpa a lista de contatos existente
		pessoa.getEnderecos().addAll(enderecosAtualizados); // Adiciona os contatos atualizados

	}

}
