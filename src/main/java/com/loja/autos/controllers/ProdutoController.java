package com.loja.autos.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.loja.autos.dto.ResponseDto;
import com.loja.autos.dto.request.ProdutoRequest;
import com.loja.autos.service.ProdutoServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "produto")
@RequiredArgsConstructor
public class ProdutoController {

	private final ProdutoServiceImpl service;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid ProdutoRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.fromData(service.register(request), HttpStatus.CREATED, "Produto cadastrado com sucesso"));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProdutoRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.update(request, id), HttpStatus.OK, "Produto atualizado com sucesso"));
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.findAll(), HttpStatus.OK, "Lista de produtos"));
	}
	
	@GetMapping(value = "/tipo-venda")
	public ResponseEntity<?> findAllTipoVenda() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.fromData(service.findAllTipoVenda(), HttpStatus.OK, "Lista de produtos tipo venda"));
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> destroy(@PathVariable UUID id){
		service.destroy(id);
		return ResponseEntity.noContent().build();
	}
}
