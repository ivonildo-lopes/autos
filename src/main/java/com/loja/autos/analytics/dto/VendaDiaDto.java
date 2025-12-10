package com.loja.autos.analytics.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VendaDiaDto {
	
	private LocalDateTime data;
	
	private Long quantidadeVendas;
	
	private BigDecimal totalVendido;

}
