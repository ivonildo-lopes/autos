package com.loja.autos.analytics.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.loja.autos.analytics.dto.CaixaAtualDto;
import com.loja.autos.analytics.dto.ClienteTopDto;
import com.loja.autos.analytics.dto.ProdutoMaisVendidoDto;
import com.loja.autos.analytics.dto.TicketMedioDto;
import com.loja.autos.analytics.dto.TotalVendidoDiaAtualDto;
import com.loja.autos.analytics.dto.VendaDiaDto;
import com.loja.autos.analytics.dto.VendasFormaPagamentoDto;
import com.loja.autos.analytics.repositorys.AnalyticsRepository;
import com.loja.autos.enums.FormaPagamento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AnalyticsRepository repository;

    public List<VendaDiaDto> vendasPorDia() {
        return repository.vendasPorDia().stream().map(r ->
        VendaDiaDto.builder()
                        .data((java.time.LocalDateTime) r[0])
                        .quantidadeVendas((Long) r[1])
                        .totalVendido((BigDecimal) r[2])
                        .build()
        ).toList();
    }

    public CaixaAtualDto caixaAtual() {
        BigDecimal valor = repository.caixaAtual();
        return CaixaAtualDto.builder().saldoAtual(valor).build();
    }

    public ClienteTopDto clienteTop() {
        Object[] r = repository.clienteQueMaisCompra().get(0);

        return ClienteTopDto.builder()
                .nome((String) r[0])
                .totalGasto((BigDecimal) r[1])
                .quantidadeCompras((Long) r[2])
                .build();
    }

    public List<ProdutoMaisVendidoDto> produtosMaisVendidos() {
        return repository.produtosMaisVendidos().stream().map(r ->
        ProdutoMaisVendidoDto.builder()
                        .nomeProduto((String) r[0])
                        .totalArrecadado((BigDecimal) r[1])
                        .build()
        ).toList();
    }

    public TicketMedioDto ticketMedio() {
        return TicketMedioDto.builder()
                .ticketMedio(repository.ticketMedio())
                .build();
    }

    public List<VendasFormaPagamentoDto> vendasPorFormaPagamento() {
        return repository.vendasPorFormaPagamento().stream().map(r ->
        VendasFormaPagamentoDto.builder()
                        .formaPagamento((FormaPagamento) r[0])
                        .quantidadeVendas((Long) r[1])
                        .totalVendido((BigDecimal) r[2])
                        .build()
        ).toList();
    }
    
    public TotalVendidoDiaAtualDto totalVendidoDia() {
    	BigDecimal valor = repository.totalVendidoHoje();
    	
        return TotalVendidoDiaAtualDto.builder()
                .totalVendido(valor)
                .build();
    }
}