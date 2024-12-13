package com.loja.autos.mappers;

import com.loja.autos.dto.request.UsuarioRequest;
import com.loja.autos.entity.Pessoa;
import com.loja.autos.entity.Usuario;

public class UsuarioMapper {
	
	public static Usuario conververToModel(UsuarioRequest request, Pessoa pessoa) {
		Usuario usuario = new Usuario();
		usuario.setEmail(request.getEmail());
		usuario.setSenha(request.getSenha());
		usuario.setAtivo(request.getAtivo());
		usuario.setPessoa(pessoa);
		return usuario;
	}
	
	public static Usuario conververToModel(UsuarioRequest request, Usuario usuario, Pessoa pessoa) {
		usuario.setEmail(request.getEmail());
		usuario.setSenha(request.getSenha());
		usuario.setAtivo(request.getAtivo());
		usuario.setPessoa(pessoa);
		return usuario;
	}

}
