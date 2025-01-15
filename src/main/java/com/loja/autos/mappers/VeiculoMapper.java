package com.loja.autos.mappers;

import org.springframework.beans.BeanUtils;

import com.loja.autos.dto.request.VeiculoRequest;
import com.loja.autos.dto.response.VeiculoResponse;
import com.loja.autos.entity.Veiculo;

public class VeiculoMapper {
	
	public static Veiculo conververToModel(VeiculoRequest request) {
		Veiculo veiculo = new Veiculo();
		BeanUtils.copyProperties(request, veiculo);
		return veiculo;
	}
	
	public static Veiculo conververToModel(VeiculoRequest request, Veiculo veiculoBase) {
		BeanUtils.copyProperties(request, veiculoBase, "statusVeiculo", "id");
		return veiculoBase;
	}
	
	public static VeiculoResponse conververToResponse(Veiculo model) {
		VeiculoResponse response = new VeiculoResponse();
		BeanUtils.copyProperties(model, response);
		return response;
	}

}
