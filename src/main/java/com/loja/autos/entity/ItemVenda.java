package com.loja.autos.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.loja.autos.enums.TipoUnidade;

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
@Table(name = "tb_itens_vendas")
public class ItemVenda {
	
	@Id
	@GeneratedValue
	@Column(name = "id_item_venda")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name = "id_venda", nullable = false)
	private Venda venda;
	
	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;
	
	@Enumerated(EnumType.STRING)
	private TipoUnidade tipoUnidade;
	
	@Column(precision = 12, scale = 4)
	private Double pesoEmkg;
	
	private Integer quantidadeUnidade;
	
	@Column(precision = 12, scale = 2)
	private BigDecimal valorUnitario;
	
	@Column(precision = 12, scale = 2)
	private BigDecimal valorTotalItem;
	

}
