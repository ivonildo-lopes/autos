package com.loja.autos.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import com.loja.autos.enums.TipoUnidade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ItemVendaResponse {
	
	private UUID id;
	
	private UUID idProduto;
	
	private String nomeProduto;
	
	private String categoriaProduto;
	
	private BigDecimal precoProduto;
	
	private TipoUnidade tipoUnidadeProduto;
	
	private Double pesoEmkg;
	
	private Integer quantidadeUnidade;
	
	private BigDecimal valorUnitario;
	
	private BigDecimal valorTotalItem;
	

}
