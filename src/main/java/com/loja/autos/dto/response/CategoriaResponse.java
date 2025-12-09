package com.loja.autos.dto.response;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoriaResponse implements Serializable {

	private static final long serialVersionUID = 1L;

    private UUID id;

    private String descricao;

}
