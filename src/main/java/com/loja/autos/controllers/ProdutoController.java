package com.loja.autos.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.request.ProdutoRequest;
import com.loja.autos.dto.response.ProdutoResponse;
import com.loja.autos.entity.Produto;
import com.loja.autos.service.ProdutoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "produto")
public class ProdutoController {

	private final ProdutoServiceImpl service;
	
	ProdutoController(ProdutoServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping
	public ProdutoResponse save(@RequestBody @Valid ProdutoRequest request) {
		return service.register(request);
	}
	
//	@PutMapping(value = "/{id}")
//	public CategoriaResponse update(@PathVariable(value = "id") UUID id, @RequestBody @Valid CategoriaRequest request) {
//		return service.update(request, id);
//	}
//	
	@GetMapping
	public List<Produto> findAll() {
		return service.findAll();
	}
	
}
