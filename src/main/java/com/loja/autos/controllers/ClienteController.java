package com.loja.autos.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.ResponseDto;
import com.loja.autos.dto.filter.ClienteFilter;
import com.loja.autos.dto.request.ClienteRequest;
import com.loja.autos.service.ClienteServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "cliente")
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteServiceImpl service;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid ClienteRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.fromData(service.save(request), HttpStatus.CREATED, "Cliente cadastrado com sucesso"));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClienteRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.update(request, id), HttpStatus.OK, "Cliente atualizado com sucesso"));
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.findAll(), HttpStatus.OK, "Lista de clientes"));
	}
	
	@GetMapping(value = "/filter")
	public ResponseEntity<?> findParams(@ModelAttribute ClienteFilter request) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.findFilter(request), HttpStatus.OK, "Clientes encontrado com sucesso"));
	}
	
}
