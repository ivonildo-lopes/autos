package com.loja.autos.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.request.ContasAPagarPaymentRequest;
import com.loja.autos.dto.request.ContasAPagarRequest;
import com.loja.autos.entity.Lancamento;
import com.loja.autos.service.ContasAPagarServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "contas-pagar")
public class ContasAPagarController {

	private final ContasAPagarServiceImpl service;
	
	ContasAPagarController(ContasAPagarServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping
	public String save(@RequestBody @Valid ContasAPagarRequest request) {
		return service.register(request);
	}
	
	@PatchMapping(value = "/{id}")
	public String payment(@PathVariable(value = "id") UUID id, @RequestBody @Valid ContasAPagarPaymentRequest request) {
		return service.payment(request, id);
	}
	
	@PatchMapping(value = "/cancelar/{id}")
	public String cancelar(@PathVariable(value = "id") UUID id) {
		return service.cancelar(id);
	}
	
	@GetMapping
	public List<Lancamento> findAll() {
		return service.findAll();
	}
	
}
