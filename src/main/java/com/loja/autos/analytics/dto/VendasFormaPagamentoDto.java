package com.loja.autos.analytics.dto;

import java.math.BigDecimal;

import com.loja.autos.enums.FormaPagamento;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VendasFormaPagamentoDto {
	
	private FormaPagamento formaPagamento;
	
	private Long quantidadeVendas;
	
	private BigDecimal totalVendido;

}
