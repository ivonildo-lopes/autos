package com.loja.autos.dto.request;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.loja.autos.enums.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest extends PessoaRequest implements Serializable {
	
	private static final long serialVersionUID = -1698397471603926128L;

	private UUID id;
	
	@NotBlank(message = "Favor informar o email.")
	private String email;
	
	@NotBlank(message = "Favor informar a senha.")
	private String senha;
	
	private Boolean ativo = true;
	
	private List<UUID> permissoes;
	
	private Role role;
}
