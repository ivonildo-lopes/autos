package com.loja.autos.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import com.loja.autos.enums.TipoUnidade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponse {
	
	private UUID id;

	private String nome;

	private String categoria;

	private BigDecimal preco;

	private TipoUnidade tipoUnidade;

}
