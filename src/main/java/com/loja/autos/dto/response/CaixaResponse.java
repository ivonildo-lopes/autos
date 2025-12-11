package com.loja.autos.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CaixaResponse {
	
	private UUID id;
	
	private LocalDate dataAbertura;
	
	private LocalTime horaAbertura;
	
	private BigDecimal valorInicial;
	
	private LocalDate dataFechamento;
	
	private LocalTime horaFechamento;
	
	private BigDecimal valorFinal;
	
	private String observacoes;
	
	private String nomeUsuario;

}
