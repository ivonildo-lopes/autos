package com.loja.autos.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.request.UsuarioRequest;
import com.loja.autos.entity.Usuario;
import com.loja.autos.service.UsuarioServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioController {

	private final UsuarioServiceImpl service;
	
	UsuarioController(UsuarioServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping
	public Usuario save(@RequestBody @Valid UsuarioRequest request) {
		return service.save(request);
	}
	
	@PutMapping(value = "/{id}")
	public Usuario save(@PathVariable(value = "id") UUID id, @RequestBody @Valid UsuarioRequest request) {
		return service.update(request, id);
	}
	
	@GetMapping(value = "/{documento}")
	public Usuario save(@PathVariable(value = "documento") String documento) {
		return service.findByDocumento(documento);
	}
	
}
