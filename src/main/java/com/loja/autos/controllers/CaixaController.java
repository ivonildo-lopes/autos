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

import com.loja.autos.dto.request.CaixaCloseRequest;
import com.loja.autos.dto.request.CaixaOpenRequest;
import com.loja.autos.entity.Caixa;
import com.loja.autos.service.CaixaServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "caixa")
public class CaixaController {

	private final CaixaServiceImpl service;
	
	CaixaController(CaixaServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping("/open")
	public String save(@RequestBody @Valid CaixaOpenRequest request) {
		return service.open(request);
	}
	
	@PutMapping(value = "/close/{id}")
	public String update(@PathVariable(value = "id") UUID id, @RequestBody @Valid CaixaCloseRequest request) {
		return service.close(request, id);
	}
	
	@GetMapping
	public List<Caixa> findAll() {
		return service.findAll();
	}
	
}
