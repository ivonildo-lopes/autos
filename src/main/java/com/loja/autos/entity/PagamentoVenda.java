package com.loja.autos.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.loja.autos.enums.FormaPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "tb_pagamentos_vendas")
public class PagamentoVenda {
	
	@Id
	@GeneratedValue
	@Column(name = "id_pagamento_venda")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name = "id_venda", nullable = false)
	private Venda venda;
	
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;
	
	@Column(precision = 12, scale = 2)
	private BigDecimal valorPago;
	
}
