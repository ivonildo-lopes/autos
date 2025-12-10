package com.loja.autos.analytics.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoMaisVendidoDto {
	
	private String nomeProduto;
	
	private BigDecimal totalArrecadado;

}
