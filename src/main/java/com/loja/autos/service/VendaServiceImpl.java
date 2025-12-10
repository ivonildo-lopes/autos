package com.loja.autos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.loja.autos.dto.request.ItemVendaRequest;
import com.loja.autos.dto.request.PagamentoRequest;
import com.loja.autos.dto.request.VendaRequest;
import com.loja.autos.entity.Caixa;
import com.loja.autos.entity.Cliente;
import com.loja.autos.entity.ItemVenda;
import com.loja.autos.entity.PagamentoVenda;
import com.loja.autos.entity.Produto;
import com.loja.autos.entity.Venda;
import com.loja.autos.enums.StatusVenda;
import com.loja.autos.enums.TipoUnidade;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.repository.CaixaRepository;
import com.loja.autos.repository.ClienteRepository;
import com.loja.autos.repository.ItemVendaRepository;
import com.loja.autos.repository.PagamentoVendaRepository;
import com.loja.autos.repository.ProdutoRepository;
import com.loja.autos.repository.VendaRepository;
import com.loja.autos.util.MoneyUtil;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendaServiceImpl {
	
	private final VendaRepository repository;
	
	private final PagamentoVendaRepository pagamentoVendaRepository;
	
	private final ItemVendaRepository itemVendaRepository;
	
	private final CaixaRepository caixaRepository;
	
	private final ClienteRepository clienteRepository;
	
	private final ProdutoRepository produtoRepository;
	
	
	@Transactional
	public String create(VendaRequest request) {
		
		Caixa caixa = caixaRepository.findById(request.getIdCaixa()).orElseThrow(() -> new NegocioException("Esse caixa não existe."));
		
		Venda venda = new Venda();
		venda.setCaixa(caixa);
		venda.setStatusVenda(StatusVenda.FECHADA);
		venda.setDesconto(request.getDesconto());
		venda.setObservacoes(request.getObservacoes());
		venda.setDataHora(LocalDateTime.now());
		
		//cliente
		Cliente cliente = null;
		if(request.getIdCliente() != null) {
			cliente = clienteRepository.findById(request.getIdCliente()).get();
			venda.setCliente(cliente);
		}
		
		BigDecimal somaTodosItens = BigDecimal.ZERO;
		
		//criar itens
		for(ItemVendaRequest itemRequest : request.getItens()) {
			Produto produto = produtoRepository.findById(itemRequest.getIdProduto()).orElseThrow(() -> new NegocioException("Esse produto não existe."));
			BigDecimal multiplicador = itemRequest.getTipoUnidade().equals(TipoUnidade.KILO)? BigDecimal.valueOf(itemRequest.getPesoEmKg()) : BigDecimal.valueOf(itemRequest.getQuantidadeUnidade());
			BigDecimal valorTotalItem = itemRequest.getValorUnitario().multiply(multiplicador);
			
			ItemVenda item = new ItemVenda();
			item.setProduto(produto);
			item.setVenda(venda);
			item.setTipoUnidade(itemRequest.getTipoUnidade());
			item.setPesoEmkg(itemRequest.getPesoEmKg());
			item.setQuantidadeUnidade(itemRequest.getQuantidadeUnidade());
			item.setValorUnitario(itemRequest.getValorUnitario());
			
			
			item.setValorTotalItem(valorTotalItem);
			
			venda.getItens().add(item);
			
			somaTodosItens = somaTodosItens.add(valorTotalItem);
		}
		
		BigDecimal desconto = request.getDesconto() != null? request.getDesconto() : BigDecimal.ZERO;
		
		BigDecimal valorTotalVenda = somaTodosItens.subtract(desconto);
		venda.setValorTotal(valorTotalVenda);
		
		//salvando venda na base
		Venda vendaBase = repository.save(venda);
		
		
		//salvar itens na base
		for( ItemVenda itemVenda : venda.getItens()) {
			itemVenda.setVenda(vendaBase);
			itemVendaRepository.save(itemVenda);
		}
		
		
		BigDecimal somaValoresPagamentos = BigDecimal.ZERO;
		//salvar pagamentos na base
		for(PagamentoRequest pagamentoRequest : request.getPagamentos()) {
			
			PagamentoVenda pagamentoVenda = new PagamentoVenda();
			pagamentoVenda.setVenda(vendaBase);
			pagamentoVenda.setFormaPagamento(pagamentoRequest.getFormaPagamento());
			pagamentoVenda.setValorPago(pagamentoRequest.getValorPago());
			
			pagamentoVendaRepository.save(pagamentoVenda);
			
			somaValoresPagamentos = somaValoresPagamentos.add(pagamentoRequest.getValorPago());
		}
		
		cliente.setDataUltimaCompra(LocalDate.now());
		cliente.setNotificacaoAusencia1(null);
		cliente.setNotificacaoAusencia2(null);
		cliente.setAtivo(true);
		clienteRepository.save(cliente);
		
		if(somaValoresPagamentos.compareTo(valorTotalVenda) != 0) {
			throw new NegocioException("Soma dos pagamentos (" +  somaValoresPagamentos + ") != total da venda ( "  + valorTotalVenda + " ) falta " + MoneyUtil.converterString(valorTotalVenda.subtract(somaValoresPagamentos)));
		}
		
		return "Venda Realizada com sucesso";
	}
	
	public Venda findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Essa categoria não existe."));
	}
	
	public List<Venda> findAll() {
		return repository.findAll();
	}
	

}
