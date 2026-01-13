package com.loja.autos.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.loja.autos.enums.FormaPagamento;
import com.loja.autos.enums.StatusLancamento;
import com.loja.autos.enums.TipoLancamento;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ContasAPagarResponse implements Serializable {


	private static final long serialVersionUID = 1L;

	private UUID id;
		
	private TipoLancamento tipoLancamento;
	
	private StatusLancamento statusLancamento;
	
	private String categoria;
	
	private String descricao;
	
	private LocalDate dataVencimento;
	
	private LocalDate dataPagamento;
	
	private BigDecimal valor;
	
	private FormaPagamento formaPagamento;
	
	private String caixa;
}
