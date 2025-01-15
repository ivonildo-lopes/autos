package com.loja.autos.mappers;

import com.loja.autos.dto.request.ClienteRequest;
import com.loja.autos.entity.Cliente;
import com.loja.autos.entity.Pessoa;

public class ClienteMapper {
	
	public static Cliente converterToModel(ClienteRequest request, Pessoa pessoa) {
		Cliente cliente = new Cliente();
		cliente.setEmail(request.getEmail());
		cliente.setPessoa(pessoa);
		return cliente;
	}
	
	
	public static Cliente converterToModel(ClienteRequest request, Cliente cliente, Pessoa pessoa) {
		cliente.setEmail(request.getEmail());
		cliente.setPessoa(pessoa);
		return cliente;
	}

}
