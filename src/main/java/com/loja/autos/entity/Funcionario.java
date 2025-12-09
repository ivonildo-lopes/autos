package com.loja.autos.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "tb_funcionarios")
public class Funcionario {
	
	@Id
	@GeneratedValue
	@Column(name = "id_funcionario")
	private UUID id;
	
	@OneToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	private String email;
	
	private String telefone;
	
	private String cargo;
	
	private LocalDate dataAdmissao;
	
	private LocalDate dataDemissao;
	
	private boolean ativo = true;

}
