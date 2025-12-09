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

import com.loja.autos.dto.request.FuncionarioRequest;
import com.loja.autos.entity.Funcionario;
import com.loja.autos.service.FuncionarioServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "funcionario")
@RequiredArgsConstructor
public class CategoriaController {

	private final FuncionarioServiceImpl service;
	
	@PostMapping
	public Funcionario save(@RequestBody @Valid FuncionarioRequest request) {
		return service.save(request);
	}
	
	@PutMapping(value = "/{id}")
	public Funcionario update(@PathVariable(value = "id") UUID id, @RequestBody @Valid FuncionarioRequest request) {
		return service.update(request, id);
	}
	
	@GetMapping
	public List<Funcionario> findAll() {
		return service.findAll();
	}
	
}
