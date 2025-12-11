package com.loja.autos.dto.response;

import java.math.BigDecimal;

import com.loja.autos.enums.FormaPagamento;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PagamentoVendaResponse {
	
	private FormaPagamento formaPagamento;
	
	private BigDecimal valorPago;
	
}
