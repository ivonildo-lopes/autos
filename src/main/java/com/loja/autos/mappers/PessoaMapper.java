package com.loja.autos.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.loja.autos.dto.request.ClienteRequest;
import com.loja.autos.dto.request.EnderecoRequest;
import com.loja.autos.dto.request.PessoaRequest;
import com.loja.autos.dto.request.UsuarioRequest;
import com.loja.autos.entity.Endereco;
import com.loja.autos.entity.Pessoa;

public class PessoaMapper {
	
	public static Pessoa dtoToModel(PessoaRequest request) {
		Pessoa pessoa = new Pessoa();
		BeanUtils.copyProperties(request, pessoa);
		setaEndereco(request.getEnderecos(), pessoa);
		return pessoa;
	}
	
	public static PessoaRequest clienteToPessoaRequest(ClienteRequest request) {
		PessoaRequest pessoa = new PessoaRequest();
		BeanUtils.copyProperties(request, pessoa);
		return pessoa;
	}
	
	public static PessoaRequest usuarioToPessoaRequest(UsuarioRequest request) {
		PessoaRequest pessoa = new PessoaRequest();
		BeanUtils.copyProperties(request, pessoa);
		return pessoa;
	}
	
	private static void setaEndereco(List<EnderecoRequest> enderecosRequest, final Pessoa pessoa) {
		List<Endereco> enderecos = enderecosRequest.stream().map(er -> {
            Endereco endereco = new Endereco();
            BeanUtils.copyProperties(er, endereco);
            endereco.setPessoa(pessoa);
            return endereco;
        }).collect(Collectors.toList());
		
		pessoa.setEnderecos(enderecos);
	}

}
