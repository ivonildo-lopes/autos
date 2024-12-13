package com.loja.autos.dto.request;

import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest extends PessoaRequest implements Serializable {
	
	private static final long serialVersionUID = -2781208723040063012L;
    
	private UUID id;
	
	@NotBlank(message = "Favor informar o email.")
	private String email;
	
}
