package com.loja.autos.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.ProdutoRequest;
import com.loja.autos.dto.response.ProdutoResponse;
import com.loja.autos.entity.Produto;
import com.loja.autos.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl {
	
//	private final ProdutoRepository repository;
//	
//	ProdutoServiceImpl(ProdutoRepository repository) {
//		this.repository = repository;
//	}
//	
//	@Transactional
//	public ProdutoResponse register(ProdutoRequest request) {
//		
//		var produtoModel = Produto.builder()
//				.categoria(request.getCategoria())
//				.nome(request.getNome())
//				.preco(request.getPreco())
//				.tipoUnidade(request.getTipoUnidade()).build();
//		
//		
//		var produtoBase = repository.save(produtoModel);
//		
//		var response = new ProdutoResponse();
//		BeanUtils.copyProperties(produtoBase, response);
//		return response;
//		
//	}
	
//	@Transactional
//	public CategoriaResponse update(CategoriaRequest request, UUID id) {
//
//		var categoriaBAse = findById(id);
//		
//		var veiculo = CategoriaMapper.conververToModel(request, categoriaBAse);
//		
//		var response = CategoriaMapper.conververToResponse(repository.save(veiculo));
//		
//		return response;
//	}
//	
//	
//	public Categoria findById(UUID id) {
//		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Essa categoria não existe."));
//	}
//	
//	public List<CategoriaResponse> findAll() {
//		return CategoriaMapper.conververToResponse(repository.findAll());
//	}

}
