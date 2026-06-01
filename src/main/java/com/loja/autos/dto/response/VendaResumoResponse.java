package com.loja.autos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendaResumoResponse {
	private String id;
	private String dataHora;
	private String nomeCaixa;
	private String statusVenda;
	private Double totalVenda;
	private List<String> formasPagamento;
}