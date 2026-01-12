package com.loja.autos.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_caixas")
public class Caixa {
	
	@Id
	@GeneratedValue
	@Column(name = "id_caixa")
	private UUID id;
	
	private String nome;
	
	private LocalDate dataAbertura;
	
	private LocalTime horaAbertura;
	
	@Column(precision = 12, scale = 2)
	private BigDecimal valorInicial = BigDecimal.ZERO;
	
	private LocalDate dataFechamento;
	
	private LocalTime horaFechamento;
	
	@Column(precision = 12, scale = 2)
	private BigDecimal valorFinal;
	
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

}
