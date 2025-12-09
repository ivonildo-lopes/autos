package com.loja.autos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.loja.autos.enums.StatusVenda;

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
@Table(name = "tb_vendas")
public class Venda {
	
	@Id
	@GeneratedValue
	@Column(name = "id_venda")
	private UUID id;
	
	private LocalDateTime dataHora;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_caixa", nullable = false)
	private Caixa caixa;
	
	@Column(precision = 12, scale = 2)
	private BigDecimal desconto;
	
	@Column(precision = 12, scale = 2)
	private BigDecimal valorTotal;
	
	@Enumerated(EnumType.STRING)
	private StatusVenda statusVenda;
	
	private List<ItemVenda> itens = new ArrayList<>();
	
	private List<PagamentoVenda> pagamentos = new ArrayList<>();
	
	private String observacoes;
	

}
