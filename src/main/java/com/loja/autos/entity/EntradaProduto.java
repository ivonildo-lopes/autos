package com.loja.autos.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "tb_entrada_produto")
public class EntradaProduto {
	
	@Id
	@GeneratedValue
	@Column(name = "id_entrada_produto")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;
	
	private BigDecimal quantidade;
	
	private BigDecimal preco;
	
	private BigDecimal valorTotal;
	
	private LocalDate dataCompra;
	
	private String fornecedor;

}
