package com.loja.autos.dto.request;

import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest implements Serializable {

	private static final long serialVersionUID = -2899630766632575841L;

	private UUID id;
	
	@NotBlank(message = "Favor informar o cep.")
	private String cep;
	
	@NotBlank(message = "Favor informar o logradouro.")
	private String logradouro;
	
	@NotBlank(message = "Favor informar o numero.")
	private String numero;
	
	private String complemento;
	
	@NotBlank(message = "Favor informar o bairro.")
	private String bairro;
	
	@NotBlank(message = "Favor informar o municipio.")
	private String municipio;
	
	@NotBlank(message = "Favor informar o estado.")
	private String uf;
}
