package com.loja.autos.dto.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.loja.autos.enums.TipoDocumento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaRequest implements Serializable {
	
	private static final long serialVersionUID = -7788500091858672780L;

	@NotBlank(message = "Favor informar o nome.")
	private String nome;
	
	@NotBlank(message = "Favor informar o documento.")
	private String documento;
	
	@NotNull(message = "Favor informar o tipo documento.")
	private TipoDocumento tipoDocumento;
	
	@NotNull(message = "Favor informar a data de nascimento.")
	private LocalDate dataNascimento;
	
	@NotNull(message = "Favor informar o(s) endereco(s).")
	private List<EnderecoRequest> enderecos;
	
}
