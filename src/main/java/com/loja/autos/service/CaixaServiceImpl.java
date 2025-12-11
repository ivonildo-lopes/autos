package com.loja.autos.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.CaixaCloseRequest;
import com.loja.autos.dto.request.CaixaOpenRequest;
import com.loja.autos.dto.response.CaixaResponse;
import com.loja.autos.entity.Caixa;
import com.loja.autos.entity.Usuario;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.repository.CaixaRepository;
import com.loja.autos.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CaixaServiceImpl {
	
	private final CaixaRepository repository;
	
	private final UsuarioRepository usuarioRepository;
	
	@Transactional
	public String open(CaixaOpenRequest request) {
		
		var caixa = new Caixa();
		caixa.setDataAbertura(LocalDate.now());
		caixa.setHoraAbertura(LocalTime.now());
		caixa.setUsuario(getUsuario());
		caixa.setValorInicial(request.getValorInicial());
		
		repository.save(caixa);
		
		return "Caixa aberto";
	}
	
	private Usuario getUsuario() {
		// pegar usuário logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName(); // email, login, etc

        // buscar o usuário no banco
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		return usuario;
	}

	@Transactional
	public String close(CaixaCloseRequest request, UUID id) {

		var caixaBase = findById(id);
		caixaBase.setDataFechamento(LocalDate.now());
		caixaBase.setHoraFechamento(LocalTime.now());
		caixaBase.setValorFinal(request.getValorFinal());
		
		repository.save(caixaBase);
		
		return "Caixa fechado";
	}
	
	
	public Caixa findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Esse caixa não existe."));
	}
	
	public List<CaixaResponse> findAll() {
		var lista = repository.findAll();
		
		return lista.stream().map(c -> converterResponse(c))
				.collect(Collectors.toList());
	}

	public CaixaResponse converterResponse(Caixa c) {
		return CaixaResponse.builder()
				.dataAbertura(c.getDataAbertura())
				.horaAbertura(c.getHoraAbertura())
				.dataFechamento(c.getDataFechamento())
				.horaFechamento(c.getHoraFechamento())
				.nomeUsuario(c.getUsuario().getPessoa().getNome())
				.valorInicial(c.getValorInicial())
				.valorFinal(c.getValorFinal())
				.observacoes(c.getObservacoes())
				.id(c.getId())
				.build();
	}

}
