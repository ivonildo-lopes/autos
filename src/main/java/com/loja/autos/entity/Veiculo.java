package com.loja.autos.entity;

import java.io.Serializable;
import java.util.UUID;

import com.loja.autos.enums.StatusVeiculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_veiculo")
public class Veiculo implements Serializable {
	
	private static final long serialVersionUID = 1678945405350212515L;

	@Id
	@GeneratedValue
	@Column(name = "id_veiculo")
	private UUID id;
	
	@Column(unique = true)
	private String placa;
	
	@Column(unique = true)
	private String renavam;
	
	@Column(unique = true)
	private String chassi;
	
	private String marca;
	
	private String modelo;
	
	private String cor;
	
	@Column(name = "ano_fabricacao")
	private String anoFabricacao;
	
	@Column(name = "ano_modelo")
	private String anoModelo;
	
	@Column(name = "tipo_veiculo")
	private String tipoVeiculo;
	
	private String quilometragem;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_veiculo")
	private StatusVeiculo statusVeiculo;

}
