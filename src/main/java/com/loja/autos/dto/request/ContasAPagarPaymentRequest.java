package com.loja.autos.dto.request;

import java.io.Serializable;
import java.time.LocalDate;

import com.loja.autos.enums.FormaPagamento;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Requisição para quitar lançamento")
public class ContasAPagarPaymentRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Favor informar a data de vencimento.")
	private LocalDate dataPagamento;
	
	@NotNull(message = "Favor informar a forma de pagamento.")
	private FormaPagamento formaPagamento;

}
