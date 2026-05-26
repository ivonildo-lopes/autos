package com.loja.autos.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.loja.autos.dto.request.ItemVendaRequest;
import com.loja.autos.dto.request.PagamentoRequest;
import com.loja.autos.dto.request.VendaRequest;
import com.loja.autos.dto.response.ItemVendaResponse;
import com.loja.autos.dto.response.PagamentoVendaResponse;
import com.loja.autos.dto.response.VendaResponse;
import com.loja.autos.entity.Caixa;
import com.loja.autos.entity.Cliente;
import com.loja.autos.entity.ItemVenda;
import com.loja.autos.entity.PagamentoVenda;
import com.loja.autos.entity.Produto;
import com.loja.autos.entity.Venda;
import com.loja.autos.enums.StatusVenda;
import com.loja.autos.enums.TipoUnidade;
import com.loja.autos.exceptions.NegocioException;
import com.loja.autos.repository.ItemVendaRepository;
import com.loja.autos.repository.PagamentoVendaRepository;
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
	
	private final CaixaServiceImpl caixaServiceImpl;
	
	private final ClienteServiceImpl clienteServiceImpl;
	
	private final ProdutoServiceImpl produtoServiceImpl;
	
	
	@Transactional
	public String create(VendaRequest request) {
		
		Caixa caixa = caixaServiceImpl.findById(request.getIdCaixa());
		
		Venda venda = new Venda();
		venda.setCaixa(caixa);
		venda.setStatusVenda(StatusVenda.FECHADA);
		venda.setDesconto(request.getDesconto());
		venda.setObservacoes(request.getObservacoes());
		venda.setDataHora(LocalDateTime.now());
		
		//cliente
		Cliente cliente = null;
		if(request.getIdCliente() != null) {
			cliente = clienteServiceImpl.findById(request.getIdCliente());
			venda.setCliente(cliente);
		}
		
		BigDecimal somaTodosItens = BigDecimal.ZERO;
		
		//criar itens
		for(ItemVendaRequest itemRequest : request.getItens()) {
			Produto produto = produtoServiceImpl.findById(itemRequest.getIdProduto());
			BigDecimal multiplicador = itemRequest.getTipoUnidade().equals(TipoUnidade.KILO)? BigDecimal.valueOf(itemRequest.getPesoEmKg()) : BigDecimal.valueOf(itemRequest.getQuantidadeUnidade());
//			BigDecimal valorTotalItem = itemRequest.getValorUnitario().multiply(multiplicador);
			BigDecimal valorTotalItem = produto.getPreco().multiply(multiplicador);
			
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
		
		if(cliente != null)
			clienteServiceImpl.saveClientPosVenda(cliente);
		
		if(somaValoresPagamentos.setScale(2, RoundingMode.HALF_UP).compareTo(valorTotalVenda.setScale(2, RoundingMode.HALF_UP)) != 0) {
			throw new NegocioException("Soma dos pagamentos (" +  somaValoresPagamentos + ") != total da venda ( "  + valorTotalVenda + " ) falta " + MoneyUtil.converterString(valorTotalVenda.subtract(somaValoresPagamentos)));
		}
		
		return "Venda Realizada com sucesso";
	}
	
	public Venda findById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new NegocioException("Essa venda não existe."));
	}
	
	public VendaResponse consultaDetalhe(UUID id) {
		return converterResponse(this.findById(id));
	}

	private VendaResponse converterResponse(Venda vendaSaved) {
		return VendaResponse.builder()
				.id(vendaSaved.getId())
				.caixa(caixaServiceImpl.converterResponse(vendaSaved.getCaixa()))
				.cliente(vendaSaved.getCliente() == null? null : clienteServiceImpl.converterResponse(vendaSaved.getCliente()))
				.dataHora(vendaSaved.getDataHora())
				.desconto(vendaSaved.getDesconto())
				.valorTotal(vendaSaved.getValorTotal())
				.itens(converterItensResponse(vendaSaved.getItens()))
				.pagamentos(converterPagamentosResponse(vendaSaved.getPagamentos()))
				.statusVenda(vendaSaved.getStatusVenda())
				.observacoes(vendaSaved.getObservacoes())
				.build();
	}
	
	public List<VendaResponse> findAll() {
		return repository.findAll().stream().map(v -> converterResponse(v)).collect(Collectors.toList());
	}
	
	private List<ItemVendaResponse> converterItensResponse(List<ItemVenda> itens) {
		return itens.stream().map(it -> ItemVendaResponse.builder() 
				.id(it.getId())
				.idProduto(it.getProduto().getId())
				.nomeProduto(it.getProduto().getNome())
				.categoriaProduto(it.getProduto().getCategoria())
				.precoProduto(it.getProduto().getPreco())
				.tipoUnidadeProduto(it.getProduto().getTipoUnidade())
				.pesoEmkg(it.getPesoEmkg())
				.quantidadeUnidade(it.getQuantidadeUnidade())
				.valorUnitario(it.getValorUnitario())
				.valorTotalItem(it.getValorTotalItem())
				.build()).collect(Collectors.toList());
	}
	
	private List<PagamentoVendaResponse> converterPagamentosResponse(List<PagamentoVenda> pagamentos) {
		return pagamentos.stream().map(p-> PagamentoVendaResponse.builder()
				.formaPagamento(p.getFormaPagamento())
				.valorPago(p.getValorPago())
				.build()
				).collect(Collectors.toList());
	}
	

}
