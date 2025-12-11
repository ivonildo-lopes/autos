package com.loja.autos.mappers;

import java.time.LocalDate;

import com.loja.autos.dto.request.ContasAPagarRequest;
import com.loja.autos.entity.Categoria;
import com.loja.autos.entity.Lancamento;
import com.loja.autos.enums.StatusLancamento;
import com.loja.autos.enums.TipoLancamento;

public class ContasAPagarMapper {
	
	public static Lancamento conververToModel(ContasAPagarRequest request, Categoria categoria) {
		Lancamento lancamento = new Lancamento();
		lancamento.setTipoLancamento(TipoLancamento.CONTAS_PAGAR);
		lancamento.setValor(request.getValor());
		lancamento.setCategoria(categoria);
		lancamento.setDescricao(request.getDescricao());
		lancamento.setDataVencimento(request.getDataVencimento());
		lancamento.setDataPagamento(request.getDataPagamento());
		lancamento.setStatusLancamento(getStatus(request.getDataPagamento(), request.getDataVencimento()));
		return lancamento;
	}

	private static StatusLancamento getStatus(LocalDate dataPagamento, LocalDate dataVencimento) {
		if(dataPagamento != null)
			return StatusLancamento.PAGO;
		if(dataVencimento.isBefore(LocalDate.now())) 
			return StatusLancamento.ATRASADO;
		else
			return StatusLancamento.PENDENTE;
	}
	
}
