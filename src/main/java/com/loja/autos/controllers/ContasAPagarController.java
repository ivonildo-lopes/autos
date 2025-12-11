package com.loja.autos.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.ResponseDto;
import com.loja.autos.dto.request.ContasAPagarPaymentRequest;
import com.loja.autos.dto.request.ContasAPagarRequest;
import com.loja.autos.service.ContasAPagarServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "contas-pagar")
@RequiredArgsConstructor
public class ContasAPagarController {

	private final ContasAPagarServiceImpl service;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid ContasAPagarRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.fromData(service.register(request), HttpStatus.CREATED, "Lançamento registrado com sucesso"));
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> payment(@PathVariable(value = "id") UUID id, @RequestBody @Valid ContasAPagarPaymentRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.payment(request, id), HttpStatus.OK, "Lançamento pago com sucesso"));
	}
	
	@PatchMapping(value = "/cancelar/{id}")
	public ResponseEntity<?> cancelar(@PathVariable(value = "id") UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.cancelar(id), HttpStatus.OK, "Lançamento cancelado com sucesso"));
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.findAll(), HttpStatus.OK, "Lançamentos contas a pagar"));
	}
	
}
