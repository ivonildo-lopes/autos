package com.loja.autos.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.loja.autos.enums.StatusLancamento;
import com.loja.autos.enums.TipoLancamento;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tb_lancamentos")
public class Lancamento implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_lancamento")
	private UUID id;
		
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipoLancacamento;
	
	@Enumerated(EnumType.STRING)
	private StatusLancamento statusLancamento;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;
	
	private String descricao;
	
	private LocalDate dataVencimento;
	
	private LocalDate dataPagamento;
	
	@Column(precision = 12, scale = 2)
	private BigDecimal valor;
}
