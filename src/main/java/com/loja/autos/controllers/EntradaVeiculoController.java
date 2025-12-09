package com.loja.autos.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.request.EntradaVeiculoRequest;
import com.loja.autos.entity.EntradaVeiculo;
import com.loja.autos.service.EntradaVeiculoServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "entrada-veiculo")
@RequiredArgsConstructor
public class EntradaVeiculoController {

	private final EntradaVeiculoServiceImpl service;
	
	@PostMapping
	public EntradaVeiculo save(@RequestBody @Valid EntradaVeiculoRequest request) {
		return service.register(request);
	}
	
	@GetMapping(value = "/{id}")
	public EntradaVeiculo save(@PathVariable(value = "id") UUID idEntradaVeiculo) {
		return service.findById(idEntradaVeiculo);
	}
	
}
