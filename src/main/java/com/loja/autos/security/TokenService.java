package com.loja.autos.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.loja.autos.exceptions.JwtException;

@Service
public class TokenService {
	
	@Value("${jwt.key.secret}")
	private String keySecret;
	
	public String generateToken(UserSystem user) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(keySecret);
			
			String token = JWT.create()
							.withIssuer("api-autos") // noma da aplicação que ta gerando o token
							.withSubject(user.getUsername()) // identificar o usuario logado
							.withExpiresAt(generateExpirationDate())
//							.withPayload(Map.of("doc", user.getUsuario().getPessoa().getDocumento()))
							.withPayload(Map.of("nome", user.getUsuario().getPessoa().getNome()))
							.withPayload(Map.of("permissoes", getPermissoes(user)))
							.sign(algorithm);
			
			return token;
							
		} catch (JWTCreationException e) {
			throw new JwtException("Erro ao gerar token " + e);
		}
		
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(keySecret);
			
			return JWT.require(algorithm)
							.withIssuer("api-autos") 
							.build()
							.verify(token)
							.getSubject();
			
							
		} catch (JWTCreationException e) {
			throw new JwtException("Erro ao validar token " + e);
		}
	}
	
	private List<String> getPermissoes(UserSystem user) {
		return user.getAuthorities().stream().map(p -> p.getAuthority()).collect(Collectors.toList());
	}
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
