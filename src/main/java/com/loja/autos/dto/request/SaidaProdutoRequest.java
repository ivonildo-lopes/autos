package com.loja.autos.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

import com.loja.autos.enums.TipoSaida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaidaProdutoRequest {

	private UUID idProduto;

	private BigDecimal quantidade;

	private TipoSaida tipoSaida;

}
