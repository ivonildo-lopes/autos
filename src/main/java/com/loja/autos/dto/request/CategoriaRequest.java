package com.loja.autos.dto.request;

import java.io.Serializable;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Requisição para criação de categoria")
public class CategoriaRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Identificador único da categoria", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @NotBlank(message = "Favor informar a descrição da categoria.")
    @Schema(description = "Descrição da categoria", example = "ALUGUEL")
    private String descricao;

}
