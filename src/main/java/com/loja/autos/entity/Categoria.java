package com.loja.autos.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id
	@GeneratedValue
	@Column(name = "id_categoria")
	private UUID id;
	
	@Column(unique = true)
	private String descricao;

}
