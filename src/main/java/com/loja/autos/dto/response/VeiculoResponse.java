package com.loja.autos.dto.response;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import com.loja.autos.enums.StatusVeiculo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VeiculoResponse extends RepresentationModel<VeiculoResponse> implements Serializable {
	
	private static final long serialVersionUID = -4615731984260852255L;

	private UUID id;
	
	private String placa;
	
	private String renavam;
	
	private String chassi;
	
	private String marca;
	
	private String modelo;
	
	private String cor;
	
	private String anoFabricacao;
	
	private String anoModelo;
	
	private String tipoVeiculo;
	
	private String quilometragem;
	
	private StatusVeiculo statusVeiculo;

}
