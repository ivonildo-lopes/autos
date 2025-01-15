package com.loja.autos.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.PessoaRequest;
import com.loja.autos.dto.request.UsuarioRequest;
import com.loja.autos.entity.Pessoa;
import com.loja.autos.entity.Usuario;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.mappers.PessoaMapper;
import com.loja.autos.mappers.UsuarioMapper;
import com.loja.autos.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl {
	
	private final UsuarioRepository repository;
	
	private final PessoaServiceImpl pessoaService;
	
	UsuarioServiceImpl(UsuarioRepository repository, PessoaServiceImpl pessoaService) {
		this.repository = repository;
		this.pessoaService = pessoaService;
	}
	
	@Transactional
	public Usuario save(UsuarioRequest request) {
		
		verificaSeUsuarioJaExiste(request);
		
		Pessoa pessoa = this.pessoaService.getPessoa(PessoaMapper.usuarioToPessoaRequest(request));
		
		return repository.save(UsuarioMapper.conververToModel(request, pessoa));
	}
	
	@Transactional
	public Usuario update(UsuarioRequest request, UUID id) {
		
		PessoaRequest pessoaRequest = PessoaMapper.usuarioToPessoaRequest(request);

		Usuario usuario = findById(id);
		Pessoa pessoa = this.pessoaService.update(PessoaMapper.dtoToModel(pessoaRequest), usuario.getPessoa());
		
		return repository.save(UsuarioMapper.conververToModel(request, usuario, pessoa));
	}
	
	
	public Usuario findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse usuario não existe."));
	}
	
	public Usuario findByDocumento(String documento) {
		return this.repository.findByDocumento(documento).orElseThrow(() -> new NegocioException("Esse usuario não existe."));
	}

	private void verificaSeUsuarioJaExiste(UsuarioRequest request) {
		var usuarioBase = repository.findByDocumento(request.getDocumento());
		
		if(usuarioBase.isPresent()) {
			throw new NegocioException("Esse usuario já esta cadastrado.");
		}
	}

}
