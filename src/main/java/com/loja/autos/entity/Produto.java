package com.loja.autos.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.loja.autos.enums.TipoUnidade;
import com.loja.autos.enums.TipoUsoProduto;

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
@Table(name = "tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue
	@Column(name = "id_produto")
	private UUID id;
	
	private String nome;
	
	private String categoria;
	
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	private TipoUnidade tipoUnidade;
	
	@Enumerated(EnumType.STRING)
	private TipoUsoProduto tipoUsoProduto;

}
