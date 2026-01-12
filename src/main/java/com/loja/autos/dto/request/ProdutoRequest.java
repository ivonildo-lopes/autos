package com.loja.autos.dto.request;

import java.math.BigDecimal;

import com.loja.autos.enums.TipoUnidade;
import com.loja.autos.enums.TipoUsoProduto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequest {
	
	private String nome;
	
	private String categoria;
	
	private BigDecimal preco;
	
	private TipoUnidade tipoUnidade;
	
	private TipoUsoProduto tipoUsoProduto;
	

}
