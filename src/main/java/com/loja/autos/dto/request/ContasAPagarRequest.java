package com.loja.autos.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.loja.autos.enums.FormaPagamento;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Requisição para contas a pagar")
public class ContasAPagarRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Identificador único da categoria", example = "123e4567-e89b-12d3-a456-426614174000")
	@NotNull(message = "Favor informar a descrição da categoria.")
    private UUID idCategoria;
	
	@NotNull(message = "Favor informar a data de vencimento.")
	private LocalDate dataVencimento;
	
	private LocalDate dataPagamento;

    @NotBlank(message = "Favor informar a descrição do lancamento.")
    @Schema(description = "Descrição da categoria", example = "aluguel do ponto")
    private String descricao;
    
    @NotNull(message = "Favor informar o valor.")
    private BigDecimal valor;
    
    private UUID idCaixa;
    
    @NotNull(message = "Favor informar a forma de pagamento.")
    private FormaPagamento formaPagamento;

}
