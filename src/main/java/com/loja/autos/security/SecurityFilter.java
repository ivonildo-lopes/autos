package com.loja.autos.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.loja.autos.service.UsuarioServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {// OncePerRequestFilter roda uma vez a cada requisição

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var token = this.recuperaTokenDaRequisicao(request);
		
		if(token != null) {
			var subject = tokenService.validateToken(token); // valido o token
			
			UserSystem user = usuarioService.loadUserByUsername(subject); // pego o usuario do sistema
			
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); 
			SecurityContextHolder.getContext().setAuthentication(authentication); // coloco a authenticação do usuario no contexto
		}
		
		filterChain.doFilter(request, response);

	}

	private String recuperaTokenDaRequisicao(HttpServletRequest request) {
		
		var authorizationHeader = request.getHeader("Authorization");
		
		return authorizationHeader == null ? null: authorizationHeader.replace("Bearer ", "");
	}

}
