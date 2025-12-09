package com.loja.autos.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SmsRequestDto(
		@Size(min = 13, max = 14, message = "o número deve conter entre {min} e {max} caracteres. ex: 5585999343912 ou +5585999343912") 
		@NotBlank(message = "favor informar o número. ex: 5585999343912 ou +5585999343912") String to, 
		@NotBlank(message = "favor informar a mensagem") String message) {

}
