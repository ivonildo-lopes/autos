package com.loja.autos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.ResponseDto;
import com.loja.autos.dto.request.AuthenticationDto;
import com.loja.autos.security.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto request) {
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.login(request), HttpStatus.OK, "sucesso"));
	}
}
