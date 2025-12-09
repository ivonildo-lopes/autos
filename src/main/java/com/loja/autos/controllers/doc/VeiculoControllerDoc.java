package com.loja.autos.controllers.doc;

import java.util.UUID;

import com.loja.autos.dto.request.VeiculoRequest;
import com.loja.autos.dto.response.VeiculoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface VeiculoControllerDoc {

	@Operation(summary = "Rota para cadastrar um novo veiculo.", 
			responses = {
					@ApiResponse(responseCode = "200"), 
					@ApiResponse(responseCode = "400", ref = "badRequest"),
					@ApiResponse(responseCode = "401", ref = "unauthorized"),
					@ApiResponse(responseCode = "500", ref = "internalServerError") 
				})
	VeiculoResponse save(
			@Parameter(description = "Informações do veiculo para ser cadastrado.") VeiculoRequest request);
	
	
	
	@Operation(summary = "Rota para atualizar um veiculo.", 
			responses = {
					@ApiResponse(responseCode = "200"), 
					@ApiResponse(responseCode = "400", ref = "badRequest"),
					@ApiResponse(responseCode = "401", ref = "unauthorized"),
					@ApiResponse(responseCode = "500", ref = "internalServerError") 
				})
	VeiculoResponse update(
			@Parameter(description = "Código do veiculo.") UUID id, 
			@Parameter(description = "Informações do veiculo para ser atualizado.")  VeiculoRequest request);
	
	
	@Operation(summary = "Rota para consultar um veiculo por placa.", 
			responses = {
					@ApiResponse(responseCode = "200"), 
					@ApiResponse(responseCode = "400", ref = "badRequest"),
					@ApiResponse(responseCode = "401", ref = "unauthorized"),
					@ApiResponse(responseCode = "500", ref = "internalServerError") 
				})
	VeiculoResponse findByPlaca(
			@Parameter(description = "Placa do veiculo.") String placa);

}
