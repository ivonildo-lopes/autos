package com.loja.autos.dto.request;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoRequest implements Serializable {
	
	private static final long serialVersionUID = -5382095192689318495L;

	private UUID id;
	
	private String descricao;

}
