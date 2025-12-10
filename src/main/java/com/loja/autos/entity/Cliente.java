package com.loja.autos.entity;

import java.io.Serializable;
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
@Table(name = "tb_clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id_cliente")
	private UUID id;
	
	@OneToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	private String email;
	
	private String telefone;
	
	@Column(nullable = true)
	private LocalDate dataUltimaCompra;
	
	@Column(nullable = true)
	private Boolean notificacaoAusencia1;
	
	@Column(nullable = true)
	private Boolean notificacaoAusencia2;
	
	@Column(nullable = true)
	private Boolean ativo = true;
}
