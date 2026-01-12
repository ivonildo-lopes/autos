package com.loja.autos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.EntradaProdutoRequest;
import com.loja.autos.dto.response.ProdutoResponse;
import com.loja.autos.entity.EntradaProduto;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.repository.EntradaProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntradaProdutoServiceImpl {
	
	private final EntradaProdutoRepository repository;
	
	private final ProdutoServiceImpl produtoService;
	
	@Transactional
	public String register(EntradaProdutoRequest request) {
		
		var produto = produtoService.findById(request.getIdProduto());
		
		var ep =  new EntradaProduto();
				ep.setProduto(produto);
				ep.setPreco(request.getPreco());
				ep.setQuantidade(request.getQuantidade());
				ep.setValorTotal(request.getValorTotal());
				ep.setDataCompra(LocalDate.now());
				ep.setFornecedor(request.getFornecedor());
		
		var produtoBase = repository.save(ep);
		  
		var response = new ProdutoResponse();
		BeanUtils.copyProperties(produtoBase, response);
		
		produto.setEstoque(produto.getEstoque() == null? BigDecimal.ZERO.add(ep.getQuantidade()) : produto.getEstoque().add(ep.getQuantidade()));
		
		
		return "entrada de produto realizada com sucesso";
		
	}
	
	@Transactional
	public EntradaProduto update(EntradaProdutoRequest request, UUID id) {

		var produtoBase = findById(id);
		BeanUtils.copyProperties(request, produtoBase, "id");
		
		repository.save(produtoBase);
		
		return produtoBase;
	}
	
	public EntradaProduto findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse produto não existe."));
	}
	
	public List<EntradaProduto> findAll() {
		return repository.findAll();
	}

}
