package com.loja.autos.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record AuthenticationDto(
		@NotEmpty(message = "favor informar o login")
		String login,
		@NotEmpty(message = "favor informar a senha")
		String password
) {};