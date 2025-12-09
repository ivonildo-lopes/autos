package com.loja.autos.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.controllers.doc.VeiculoControllerDoc;
import com.loja.autos.dto.request.VeiculoRequest;
import com.loja.autos.dto.response.VeiculoResponse;
import com.loja.autos.service.VeiculoServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "veiculo")
@RequiredArgsConstructor
public class VeiculoController implements VeiculoControllerDoc {

	private final VeiculoServiceImpl service;
	
	@PostMapping
	public VeiculoResponse save(@RequestBody @Valid VeiculoRequest request) {
		return service.register(request);
	}
	
	@PutMapping(value = "/{id}")
	public VeiculoResponse update(@PathVariable(value = "id") UUID id, @RequestBody @Valid VeiculoRequest request) {
		return service.update(request, id);
	}
	
	@GetMapping(value = "/{placa}")
	public VeiculoResponse findByPlaca(@PathVariable(value = "placa") String placa) {
		return service.findByPlaca(placa);
	}
	
}
