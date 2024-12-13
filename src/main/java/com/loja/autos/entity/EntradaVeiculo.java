package com.loja.autos.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.loja.autos.enums.StatusVeiculo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_entrada_veiculo")
public class EntradaVeiculo implements Serializable {
	
	private static final long serialVersionUID = 5581222757128929263L;

	@Id
	@GeneratedValue
	@Column(name = "id_entrada_veiculo")
	private UUID id;
	
	@OneToOne
	@JoinColumn(name = "id_veiculo")
	private Veiculo veiculo;
	
	@Column(name = "data_entrada")
	private LocalDate dataEntrada;
		
	@Column(name = "valor_compra")
	private BigDecimal valorCompra;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_veiculo")
	private StatusVeiculo statusVeiculo;

}
