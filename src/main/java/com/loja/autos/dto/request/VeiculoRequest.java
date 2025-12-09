package com.loja.autos.dto.request;

import java.io.Serializable;
import java.util.UUID;

import com.loja.autos.enums.StatusVeiculo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Requisição para criação ou atualização de um veículo")
public class VeiculoRequest implements Serializable {

    private static final long serialVersionUID = -3179771224444094587L;

    @Schema(description = "Identificador único do veículo", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @NotBlank(message = "Favor informar a placa.")
    @Schema(description = "Placa do veículo", example = "ORU09668")
    private String placa;

    @NotBlank(message = "Favor informar o renavam.")
    @Schema(description = "Número do RENAVAM do veículo", example = "0065276458430")
    private String renavam;

    @NotBlank(message = "Favor informar o chassi.")
    @Schema(description = "Número do chassi do veículo", example = "9BWZZZ377VT00425411")
    private String chassi;

    @NotBlank(message = "Favor informar a marca.")
    @Schema(description = "Marca do veículo", example = "Honda")
    private String marca;

    @NotBlank(message = "Favor informar o modelo.")
    @Schema(description = "Modelo do veículo", example = "Civic LXS")
    private String modelo;

    @NotBlank(message = "Favor informar a cor.")
    @Schema(description = "Cor do veículo", example = "Preta")
    private String cor;

    @NotBlank(message = "Favor informar o ano de fabricação.")
    @Schema(description = "Ano de fabricação do veículo", example = "2019")
    private String anoFabricacao;

    @NotBlank(message = "Favor informar o ano do modelo.")
    @Schema(description = "Ano do modelo do veículo", example = "2020")
    private String anoModelo;

    @NotBlank(message = "Favor informar o tipo do veículo.")
    @Schema(description = "Tipo do veículo", example = "Carro")
    private String tipoVeiculo;

    @NotBlank(message = "Favor informar a quilometragem.")
    @Schema(description = "Quilometragem do veículo", example = "15000")
    private String quilometragem;

    @Schema(description = "Status do veículo")
    private StatusVeiculo statusVeiculo;

}
