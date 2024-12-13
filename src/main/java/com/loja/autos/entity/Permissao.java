package com.loja.autos.entity;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_permissao")
public class Permissao implements Serializable {
	
	private static final long serialVersionUID = 5066221484427101065L;

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name = "descricao", unique = true)
	private String descricao;

}
