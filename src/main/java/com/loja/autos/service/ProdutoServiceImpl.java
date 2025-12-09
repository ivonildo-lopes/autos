package com.loja.autos.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.ProdutoRequest;
import com.loja.autos.dto.response.ProdutoResponse;
import com.loja.autos.entity.Produto;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl {
	
	private final ProdutoRepository repository;
	
	@Transactional
	public ProdutoResponse register(ProdutoRequest request) {
		
		var produtoModel =  new Produto();
				produtoModel.setCategoria(request.getCategoria());
				produtoModel.setNome(request.getNome());
				produtoModel.setPreco(request.getPreco());
				produtoModel.setTipoUnidade(request.getTipoUnidade());
		
		var produtoBase = repository.save(produtoModel);
		
		var response = new ProdutoResponse();
		BeanUtils.copyProperties(produtoBase, response);
		return response;
		
	}
	
	@Transactional
	public ProdutoResponse update(ProdutoRequest request, UUID id) {

		var produtoBase = findById(id);
		BeanUtils.copyProperties(request, produtoBase, "id");
		
		
		repository.save(produtoBase);
		
		var response = new ProdutoResponse();
		BeanUtils.copyProperties(produtoBase, response);
		
		return response;
	}
	
	
	public Produto findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse produto não existe."));
	}
	
	public List<Produto> findAll() {
		return repository.findAll();
	}

}
