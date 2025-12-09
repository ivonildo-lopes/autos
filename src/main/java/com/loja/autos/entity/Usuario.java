package com.loja.autos.entity;

import java.io.Serializable;
import java.util.UUID;

import com.loja.autos.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tb_usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 2552084238061679520L;

	@Id
	@GeneratedValue
	private UUID id;
	
	@OneToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	@Column(name = "email", unique = true)
	private String email;
	
	private String senha;
	
	private Boolean ativo;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "permissao_usuario", joinColumns = @JoinColumn(name = "id_usuario"),
//    inverseJoinColumns = @JoinColumn(name = "id_permissao"))
//	@jakarta.persistence.Transient
//	private List<Permissao> permissoes;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String secret2FA;

}
