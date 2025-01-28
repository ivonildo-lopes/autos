package com.loja.autos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.loja.autos.dto.request.AuthenticationDto;

import jakarta.validation.Valid;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;

	public String login(@Valid AuthenticationDto request) {
		
		var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
		
		var auth = authenticationManager.authenticate(usernamePassword);
		
		return tokenService.generateToken((UserSystem) auth.getPrincipal());
		
	}
	
	
	public UserSystem logiBasic(@Valid AuthenticationDto request) {
		
		var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
		
		var auth = authenticationManager.authenticate(usernamePassword);
		
		return (UserSystem) auth.getPrincipal();
		
	}

}
