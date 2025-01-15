package com.loja.autos.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.loja.autos.enums.StatusVeiculo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntradaVeiculoRequest implements Serializable {
	
	private static final long serialVersionUID = 2785824455864214897L;

	private UUID id;
	
	@NotNull(message = "Favor informar o veiculo.")
	private UUID idVeiculo;
	
	@NotNull(message = "Favor informar a data da entrada.")
	private LocalDate dataEntrada;
	
	@NotNull(message = "Favor informar o valor da compra.")
	private BigDecimal valorCompra;
	
	private StatusVeiculo statusVeiculo;

}
