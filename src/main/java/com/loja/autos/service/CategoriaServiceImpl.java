package com.loja.autos.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.CategoriaRequest;
import com.loja.autos.dto.response.CategoriaResponse;
import com.loja.autos.entity.Categoria;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.mappers.CategoriaMapper;
import com.loja.autos.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl {
	
	private final CategoriaRepository repository;
	
	@Transactional
	public CategoriaResponse register(CategoriaRequest request) {
		
		var categoria = CategoriaMapper.conververToModel(request);
		
		var response = CategoriaMapper.conververToResponse(repository.save(categoria));
		
		return response;
	}
	
	@Transactional
	public CategoriaResponse update(CategoriaRequest request, UUID id) {

		var categoriaBAse = findById(id);
		
		var veiculo = CategoriaMapper.conververToModel(request, categoriaBAse);
		
		var response = CategoriaMapper.conververToResponse(repository.save(veiculo));
		
		return response;
	}
	
	public CategoriaResponse consultaPorId(UUID id) {
		return CategoriaMapper.conververToResponse(findById(id));
	}
	
	
	public Categoria findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Essa categoria não existe."));
	}
	
	public List<CategoriaResponse> findAll() {
		return CategoriaMapper.conververToResponse(repository.findAll());
	}

}
