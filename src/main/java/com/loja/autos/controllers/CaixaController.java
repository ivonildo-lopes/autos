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
import com.loja.autos.dto.request.CaixaCloseRequest;
import com.loja.autos.dto.request.CaixaOpenRequest;
import com.loja.autos.service.CaixaServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "caixa")
@RequiredArgsConstructor
public class CaixaController {

	private final CaixaServiceImpl service;
	
	@PostMapping("/open")
	public ResponseEntity<?> save(@RequestBody @Valid CaixaOpenRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.fromData(service.open(request), HttpStatus.CREATED, "Caixa Aberto"));
	}
	
	@PutMapping(value = "/close/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid CaixaCloseRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.close(request, id), HttpStatus.OK, "Caixa FEchado"));
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.findAll(), HttpStatus.OK, "Caixas"));
	}
	
	@GetMapping(value = "all-open")
	public ResponseEntity<?> findAllOpen() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.findAllOpen(), HttpStatus.OK, "Caixas"));
	}
	
}
