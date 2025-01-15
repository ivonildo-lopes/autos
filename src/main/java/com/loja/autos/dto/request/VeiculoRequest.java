package com.loja.autos.dto.request;

import java.io.Serializable;
import java.util.UUID;

import com.loja.autos.enums.StatusVeiculo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VeiculoRequest implements Serializable {
	
	private static final long serialVersionUID = -3179771224444094587L;

	private UUID id;
	
	@NotBlank(message = "Favor informar a placa.")
	private String placa;
	
	@NotBlank(message = "Favor informar o renavam.")
	private String renavam;
	
	@NotBlank(message = "Favor informar o chassi.")
	private String chassi;
	
	@NotBlank(message = "Favor informar a marca.")
	private String marca;
	
	@NotBlank(message = "Favor informar o modelo.")
	private String modelo;
	
	@NotBlank(message = "Favor informar a cor.")
	private String cor;
	
	@NotBlank(message = "Favor informar o ano de fabricação.")
	private String anoFabricacao;
	
	@NotBlank(message = "Favor informar o ano do modelo.")
	private String anoModelo;
	
	@NotBlank(message = "Favor informar o tipo do veiculo.")
	private String tipoVeiculo;
	
	@NotBlank(message = "Favor informar a quilometragem.")
	private String quilometragem;
	
	private StatusVeiculo statusVeiculo;

}
