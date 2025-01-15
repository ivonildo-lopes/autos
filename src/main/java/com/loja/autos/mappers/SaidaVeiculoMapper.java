package com.loja.autos.mappers;

import com.loja.autos.dto.request.SaidaVeiculoRequest;
import com.loja.autos.entity.Cliente;
import com.loja.autos.entity.EntradaVeiculo;
import com.loja.autos.entity.SaidaVeiculo;

public class SaidaVeiculoMapper {

	public static SaidaVeiculo converterToModel(SaidaVeiculoRequest request, Cliente cliente, EntradaVeiculo entradaVeiculo) {
		SaidaVeiculo sv = new SaidaVeiculo();
		sv.setDataSaida(request.getDataSaida());
		sv.setValorSaida(request.getValorSaida());
		sv.setEntradaVeiculo(entradaVeiculo);
		sv.setCliente(cliente);
		return sv;
	}
}
