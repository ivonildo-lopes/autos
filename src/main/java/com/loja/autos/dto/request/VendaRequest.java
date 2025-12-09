package com.loja.autos.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VendaRequest {
	
	private UUID idCaixa;
	
	private UUID idCliente;
	
	private BigDecimal desconto;
	
	private String observacoes;
	
	private List<ItemVendaRequest> itens;
	
	private List<PagamentoRequest> pagamentos;

}
