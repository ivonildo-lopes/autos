package com.loja.autos.analytics.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaixaAtualDto {
	
	private BigDecimal saldoAtual;

}
