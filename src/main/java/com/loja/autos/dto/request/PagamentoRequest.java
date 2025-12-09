package com.loja.autos.dto.request;

import java.math.BigDecimal;

import com.loja.autos.enums.FormaPagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoRequest {
	
	private FormaPagamento formaPagamento;
	
	private BigDecimal valorPago;
	
	private String observacao;

}
