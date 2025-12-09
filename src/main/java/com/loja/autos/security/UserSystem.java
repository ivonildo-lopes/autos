package com.loja.autos.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.loja.autos.entity.Usuario;
import com.loja.autos.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSystem implements UserDetails {
	
	private static final long serialVersionUID = -6203565558607971801L;
	
	private Usuario usuario;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return usuario.getRole() == Role.ADMIN ? 
				List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_VENDEDOR")) :
				List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().toString()));	
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}
	
	@Override
	public boolean isEnabled() {
		return usuario.getAtivo();
	}

}
