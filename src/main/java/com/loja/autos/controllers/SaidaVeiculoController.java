package com.loja.autos.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.request.SaidaVeiculoRequest;
import com.loja.autos.entity.SaidaVeiculo;
import com.loja.autos.service.SaidaVeiculoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "saida-veiculo")
public class SaidaVeiculoController {

	private final SaidaVeiculoServiceImpl service;
	
	SaidaVeiculoController(SaidaVeiculoServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping
	public SaidaVeiculo save(@RequestBody @Valid SaidaVeiculoRequest request) {
		return service.register(request);
	}
	
	@GetMapping(value = "/{id}")
	public SaidaVeiculo save(@PathVariable(value = "id") UUID idEntradaVeiculo) {
		return service.findById(idEntradaVeiculo);
	}
	
}
