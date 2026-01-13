package com.loja.autos.dto.response;

import java.math.BigDecimal;

public interface CaixaResumoFechamentoResponse {

	String getNome();

	BigDecimal getValorInicial();

	BigDecimal getTotalVendidoDinheiro();

	BigDecimal getTotalGastoDinheiro();

	BigDecimal getSaldoEsperado();
}
