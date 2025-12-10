package com.loja.autos.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.request.VendaRequest;
import com.loja.autos.entity.Venda;
import com.loja.autos.service.VendaServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "venda")
@RequiredArgsConstructor
public class VendaController {

	private final VendaServiceImpl service;
	
	@PostMapping
	public Venda save(@RequestBody @Valid VendaRequest request) {
		return service.create(request);
	}
	
	@GetMapping(value = "/{id}")
	public Venda update(@PathVariable(value = "id") UUID id) {
		return service.findById(id);
	}
	
	@GetMapping
	public List<Venda> findAll() {
		return service.findAll();
	}
	
}
