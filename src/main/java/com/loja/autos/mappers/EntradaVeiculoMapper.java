package com.loja.autos.mappers;

import java.time.LocalDate;

import com.loja.autos.dto.request.EntradaVeiculoRequest;
import com.loja.autos.entity.EntradaVeiculo;

public class EntradaVeiculoMapper {
	
	public static EntradaVeiculo converterToModel(EntradaVeiculoRequest request) {
		EntradaVeiculo ev = new EntradaVeiculo();
		ev.setDataEntrada(request.getDataEntrada() == null ? LocalDate.now() : request.getDataEntrada());
		ev.setValorCompra(request.getValorCompra());
		return ev;
	}

}
