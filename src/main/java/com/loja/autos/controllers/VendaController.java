package com.loja.autos.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.ResponseDto;
import com.loja.autos.dto.request.VendaRequest;
import com.loja.autos.service.VendaServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "venda")
@RequiredArgsConstructor
public class VendaController {

	private final VendaServiceImpl service;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid VendaRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.fromData(service.create(request), HttpStatus.CREATED, "Venda registrada com sucesso"));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> consultaDetalheVenda(@PathVariable(value = "id") UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.consultaDetalhe(id), HttpStatus.OK, "Detalhe da venda"));
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.findAll(), HttpStatus.OK, "Todas as vendas"));
	}
	
	@GetMapping("/vendas-hoje")
	public ResponseEntity<?> vendasHoje() {
	    return ResponseEntity.status(HttpStatus.OK)
	        .body(ResponseDto.fromData(
	            service.vendasHojePorFormaPagamento(),
	            HttpStatus.OK,
	            "Vendas do dia por forma de pagamento"
	        ));
	}
	
}
