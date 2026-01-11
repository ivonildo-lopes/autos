package com.loja.autos.dto.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class FuncionarioResponse implements Serializable {

	private UUID id;
	
	private String nome;
	
	private String email;
	
	private String cargo;
	
	private String telefone;
	
	private LocalDate dataAdmissao; 
	
	private Boolean ativo;
}
