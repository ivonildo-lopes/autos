package com.loja.autos.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loja.autos.dto.request.ContasAPagarPaymentRequest;
import com.loja.autos.dto.request.ContasAPagarRequest;
import com.loja.autos.dto.response.ContasAPagarResponse;
import com.loja.autos.entity.Lancamento;
import com.loja.autos.enums.StatusLancamento;
import com.loja.autos.enums.TipoLancamento;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.mappers.ContasAPagarMapper;
import com.loja.autos.repository.LancamentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContasAPagarServiceImpl {
	
	private final LancamentoRepository repository;
	
	private final CategoriaServiceImpl categoriaService;
	
	@Transactional
	public String register(ContasAPagarRequest request) {
		
		var categoria = categoriaService.findById(request.getIdCategoria());
		
		var cp = ContasAPagarMapper.conververToModel(request, categoria);
		
		if(cp.getValor().doubleValue() < 0)
			throw new NegocioException("o valor do lançamento não pode ser negativo");
		
		if(cp.getDataPagamento() != null && cp.getDataPagamento().isBefore(cp.getDataVencimento()))
			throw new NegocioException("a data do pagamento não pode ser anterior a data do vencimento");
		
		repository.save(cp);
		
		return "Lançamento cadastrado com sucesso";
	}
	
	@Transactional
	public String payment(ContasAPagarPaymentRequest request, UUID id) {
		
		var lancamento = findById(id);
		
		if(lancamento.getStatusLancamento().equals(StatusLancamento.PAGO)) {
			throw new NegocioException("Esse Lançamento já esta pago");
		} else if(lancamento.getStatusLancamento().equals(StatusLancamento.CANCELADO)) {
			throw new NegocioException("Esse Lançamento foi cancelado e não pode ser pago, por favor realize um novo lançamento e faça o pagamento caso seja necessario");
		}
		
		lancamento.setDataPagamento(request.getDataPagamento());
		lancamento.setStatusLancamento(StatusLancamento.PAGO);
		
		repository.save(lancamento);
		
		return "Lançamento pago com sucesso";
	}
	
	@Transactional
	public String cancelar(UUID id) {
		
		var lancamento = findById(id);
		
		if(lancamento.getStatusLancamento().equals(StatusLancamento.PAGO)) {
			throw new NegocioException("Esse Lançamento já esta pago e não pode ser cancelado");
		} else if(lancamento.getStatusLancamento().equals(StatusLancamento.CANCELADO)) {
			throw new NegocioException("Esse Lançamento já foi cancelado.");
		}
		
		lancamento.setStatusLancamento(StatusLancamento.CANCELADO);
		
		repository.save(lancamento);
		
		return "Lançamento cancelado com sucesso";
	}
	
	public List<ContasAPagarResponse> findAll() {
		return repository.findAllLancamento(TipoLancamento.CONTAS_PAGAR.toString())
				.stream().map(cp -> ContasAPagarResponse.builder()
						.id(cp.getId())
						.tipoLancamento(cp.getTipoLancamento())
						.statusLancamento(cp.getStatusLancamento())
						.categoria(cp.getCategoria().getDescricao())
						.descricao(cp.getDescricao())
						.dataVencimento(cp.getDataVencimento())
						.dataPagamento(cp.getDataPagamento())
						.valor(cp.getValor())
						.build()
						).collect(Collectors.toList());
	}
	
	private Lancamento findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Essa categoria não existe."));
	}

}
