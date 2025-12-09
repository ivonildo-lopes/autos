package com.loja.autos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loja.autos.service.UsuarioServiceImpl;

@Service
public class AuthorizationService implements UserDetailsService {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioService.loadUserByUsername(username);
	}

}
