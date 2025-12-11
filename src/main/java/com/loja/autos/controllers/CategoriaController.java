package com.loja.autos.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.ResponseDto;
import com.loja.autos.dto.request.CategoriaRequest;
import com.loja.autos.service.CategoriaServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "categoria")
@RequiredArgsConstructor
public class CategoriaController {

	private final CategoriaServiceImpl service;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid CategoriaRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.fromData(service.register(request), HttpStatus.CREATED, "Categoria cadastrada com sucesso"));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid CategoriaRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.update(request, id), HttpStatus.OK, "Categoria atualizada com sucesso"));
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.findAll(), HttpStatus.OK, "Lista de categorias"));
	}
	
}
