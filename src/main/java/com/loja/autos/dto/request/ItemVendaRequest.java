package com.loja.autos.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

import com.loja.autos.enums.TipoUnidade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVendaRequest {
	
	private UUID idProduto;
	
	private TipoUnidade tipoUnidade;
	
	private Double pesoEmKg;
	
	private Integer quantidadeUnidade;
	
	private BigDecimal valorUnitario;

}
