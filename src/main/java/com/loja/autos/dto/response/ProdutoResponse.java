package com.loja.autos.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import com.loja.autos.enums.TipoUnidade;
import com.loja.autos.enums.TipoUsoProduto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {
	
	private UUID id;

	private String nome;

	private String categoria;

	private BigDecimal preco;

	private TipoUnidade tipoUnidade;
	
	private TipoUsoProduto tipoUsoProduto;

}
