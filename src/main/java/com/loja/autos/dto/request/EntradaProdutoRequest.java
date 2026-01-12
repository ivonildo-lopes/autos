package com.loja.autos.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntradaProdutoRequest {

	private UUID idProduto;

	private BigDecimal quantidade;

	private BigDecimal preco;
	
	private BigDecimal valorTotal;
	
	private String fornecedor;

}
