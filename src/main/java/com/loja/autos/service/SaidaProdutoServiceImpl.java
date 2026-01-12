package com.loja.autos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.SaidaProdutoRequest;
import com.loja.autos.dto.response.ProdutoResponse;
import com.loja.autos.entity.SaidaProduto;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.repository.SaidaProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaidaProdutoServiceImpl {
	
	private final SaidaProdutoRepository repository;
	
	private final ProdutoServiceImpl produtoService;
	
	private final UsuarioServiceImpl usuarioService;
	
	@Transactional
	public String register(SaidaProdutoRequest request) {
		
		var produto = produtoService.findById(request.getIdProduto());
		var usuario = usuarioService.getUsuarioLogado();
		
		
		var sp =  new SaidaProduto();
				sp.setQuantidade(request.getQuantidade());
				sp.setTipoSaida(request.getTipoSaida());
				sp.setProduto(produto);
				sp.setDataMovimentacao(LocalDateTime.now());
				sp.setUsuario(usuario);
		
		var produtoBase = repository.save(sp);
		  
		var response = new ProdutoResponse();
		BeanUtils.copyProperties(produtoBase, response);
		
		// verifica se a retira é maior que esta no estoque 
		if(produto.getEstoque() != null && sp.getQuantidade().compareTo(produto.getEstoque()) == 1) {
			throw new NegocioException("o produto: " + produto.getNome() + " só tem " + produto.getEstoque() + " em estoque");
		}
		
		produto.setEstoque(produto.getEstoque().subtract(sp.getQuantidade()));
		
		//por estar usando o @Transactional o objeto produto esta sendo atualizado no banco de dados com o campo estoque - sem precisar usar o save do produto
		
		
		return "saida de produto realizada com sucesso";
		
	}
	
	@Transactional
	public SaidaProduto update(SaidaProdutoRequest request, UUID id) {

		var produtoBase = findById(id);
		BeanUtils.copyProperties(request, produtoBase, "id");
		
		repository.save(produtoBase);
		
		return produtoBase;
	}
	
	public SaidaProduto findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse produto não existe."));
	}
	
	public List<SaidaProduto> findAll() {
		return repository.findAll();
	}

}
