package com.loja.autos.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.loja.autos.enums.StatusVenda;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class VendaResponse {
	
	private UUID id;
	
	private LocalDateTime dataHora;
	
	private ClienteResponse cliente;
	private CaixaResponse caixa;
	
	private BigDecimal desconto;
	
	private BigDecimal valorTotal;
	
	private StatusVenda statusVenda;
	
	private List<ItemVendaResponse> itens;
	
	private List<PagamentoVendaResponse> pagamentos;
	
	private String observacoes;
	

}
