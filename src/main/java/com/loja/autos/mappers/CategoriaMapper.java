package com.loja.autos.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.loja.autos.dto.request.CategoriaRequest;
import com.loja.autos.dto.response.CategoriaResponse;
import com.loja.autos.entity.Categoria;

public class CategoriaMapper {
	
	public static Categoria conververToModel(CategoriaRequest request) {
		Categoria categoria = new Categoria();
		categoria.setDescricao(request.getDescricao().toUpperCase());
		return categoria;
	}
	
	public static Categoria conververToModel(CategoriaRequest request,Categoria categoria) {
		BeanUtils.copyProperties(request, categoria, "id");
		return categoria;
	}
	
	public static CategoriaResponse conververToResponse(Categoria model) {
		CategoriaResponse response = new CategoriaResponse();
		BeanUtils.copyProperties(model, response);
		return response;
	}

	public static List<CategoriaResponse> conververToResponse(List<Categoria> categoriasBase) {
		return categoriasBase.stream().map(c-> conververToResponse(c)).collect(Collectors.toList());
	}

}
