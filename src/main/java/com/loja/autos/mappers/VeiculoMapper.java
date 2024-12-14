package com.loja.autos.mappers;

import org.springframework.beans.BeanUtils;

import com.loja.autos.dto.request.VeiculoRequest;
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

}
