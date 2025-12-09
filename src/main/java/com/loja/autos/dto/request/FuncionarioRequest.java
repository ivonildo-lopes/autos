package com.loja.autos.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioRequest extends PessoaRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UUID id;
	
	@NotBlank(message = "Favor informar o email.")
	private String email;
	
	private String telefone;
	
	private String cargo;
	
	private BigDecimal salario;
	
	private LocalDate dataAdmissao;
	
}
