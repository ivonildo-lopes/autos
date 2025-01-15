package com.loja.autos.mappers;

import java.time.LocalDate;

import com.loja.autos.dto.request.EntradaVeiculoRequest;
import com.loja.autos.entity.EntradaVeiculo;
import com.loja.autos.entity.Veiculo;
import com.loja.autos.enums.StatusVeiculo;

public class EntradaVeiculoMapper {
	
	public static EntradaVeiculo converterToModel(EntradaVeiculoRequest request, Veiculo veiculo, StatusVeiculo status) {
		EntradaVeiculo ev = new EntradaVeiculo();
		ev.setDataEntrada(request.getDataEntrada() == null ? LocalDate.now() : request.getDataEntrada());
		ev.setValorCompra(request.getValorCompra());
		ev.setVeiculo(veiculo);
		ev.setStatusVeiculo(status);
		return ev;
	}

}
