package com.loja.autos.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.request.ClienteRequest;
import com.loja.autos.entity.Cliente;
import com.loja.autos.service.ClienteServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "cliente")
public class ClienteController {

	private final ClienteServiceImpl service;
	
	ClienteController(ClienteServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping
	public Cliente save(@RequestBody @Valid ClienteRequest request) {
		return service.save(request);
	}
	
	@PutMapping(value = "/{id}")
	public Cliente save(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClienteRequest request) {
		return service.update(request, id);
	}
	
}
