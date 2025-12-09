package com.loja.autos.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.request.CategoriaRequest;
import com.loja.autos.dto.response.CategoriaResponse;
import com.loja.autos.service.CategoriaServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "categoria")
public class CategoriaController {

	private final CategoriaServiceImpl service;
	
	CategoriaController(CategoriaServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping
	public CategoriaResponse save(@RequestBody @Valid CategoriaRequest request) {
		return service.register(request);
	}
	
	@PutMapping(value = "/{id}")
	public CategoriaResponse update(@PathVariable(value = "id") UUID id, @RequestBody @Valid CategoriaRequest request) {
		return service.update(request, id);
	}
	
	@GetMapping
	public List<CategoriaResponse> findAll() {
		return service.findAll();
	}
	
}
