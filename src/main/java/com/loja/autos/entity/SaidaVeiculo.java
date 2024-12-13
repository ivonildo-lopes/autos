package com.loja.autos.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tb_saida_veiculo")
public class SaidaVeiculo implements Serializable {
	
	private static final long serialVersionUID = -7200749465051107330L;

	@Id
	@GeneratedValue
	@Column(name = "id_saida_veiculo")
	private UUID id;
	
	@OneToOne
	@JoinColumn(name = "id_entrada_veiculo")
	private EntradaVeiculo entradaVeiculo;
	
	@OneToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Column(name = "data_saida")
	private LocalDate dataSaida;
		
	@Column(name = "valor_saida")
	private BigDecimal valorSaida;
	
}
