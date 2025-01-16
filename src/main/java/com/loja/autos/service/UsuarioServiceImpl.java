package com.loja.autos.service;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.loja.autos.security.UserSystem;

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
		
		Usuario usuario = UsuarioMapper.conververToModel(request, pessoa);
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		return repository.save(usuario);
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
	
	public UserSystem loadUserByUsername(String email) {
		var usuarioBase = repository.findByEmail(email);
		
		if(!usuarioBase.isPresent()) {
			throw new NegocioException("Erro ao tentar logar.");
		}
		
		return new UserSystem(usuarioBase.get());
	}

	private void verificaSeUsuarioJaExiste(UsuarioRequest request) {
		var usuarioBase = repository.findByDocumento(request.getDocumento());
		
		if(usuarioBase.isPresent()) {
			throw new NegocioException("Esse usuario já esta cadastrado.");
		}
	}

}
