package com.loja.autos.dto.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaixaOpenRequest {

	private BigDecimal valorInicial;
	
	private String nome;
}
