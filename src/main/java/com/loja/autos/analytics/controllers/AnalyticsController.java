package com.loja.autos.analytics.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.analytics.dto.CaixaAtualDto;
import com.loja.autos.analytics.dto.ClienteTopDto;
import com.loja.autos.analytics.dto.ProdutoMaisVendidoDto;
import com.loja.autos.analytics.dto.TicketMedioDto;
import com.loja.autos.analytics.dto.TotalVendidoDiaAtualDto;
import com.loja.autos.analytics.dto.VendaDiaDto;
import com.loja.autos.analytics.dto.VendasFormaPagamentoDto;
import com.loja.autos.analytics.services.AnalyticsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "analista")
@RequiredArgsConstructor
public class AnalyticsController {
	
	private final AnalyticsService service;

    @GetMapping("/vendas-dia")
    public List<VendaDiaDto> vendasDia() {
        return service.vendasPorDia();
    }

    @GetMapping("/caixa-atual")
    public CaixaAtualDto caixaAtual() {
        return service.caixaAtual();
    }

    @GetMapping("/cliente-top")
    public ClienteTopDto clienteQueMaisCompra() {
        return service.clienteTop();
    }

    @GetMapping("/produtos-mais-vendidos")
    public List<ProdutoMaisVendidoDto> produtosMaisVendidos() {
        return service.produtosMaisVendidos();
    }

    @GetMapping("/ticket-medio")
    public TicketMedioDto ticketMedio() {
        return service.ticketMedio();
    }

    @GetMapping("/pagamentos")
    public List<VendasFormaPagamentoDto> vendasPorFormaPagamento() {
        return service.vendasPorFormaPagamento();
    }
    
    @GetMapping("/total-vendido-do-dia")
    public TotalVendidoDiaAtualDto totalVendidoDia() {
        return service.totalVendidoDia();
    }

}
