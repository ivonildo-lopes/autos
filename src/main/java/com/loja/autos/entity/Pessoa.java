package com.loja.autos.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.loja.autos.enums.TipoDocumento;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_pessoa")
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 3944304417540305770L;

	@Id
	@GeneratedValue
	@Column(name = "id_pessoa")
	private UUID id;
	
	private String nome;
	
	@Column(name = "documento", unique = true)
	private String documento;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_documento")
	private TipoDocumento tipoDocumento;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

}
