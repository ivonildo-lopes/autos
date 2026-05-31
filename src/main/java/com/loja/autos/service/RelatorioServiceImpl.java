package com.loja.autos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.loja.autos.dto.response.DashboardResumoResponse;
import com.loja.autos.dto.response.FaturamentoDiarioResponse;
import com.loja.autos.dto.response.FaturamentoMensalResponse;
import com.loja.autos.repository.CaixaRepository;
import com.loja.autos.repository.RelatorioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelatorioServiceImpl {

    private final RelatorioRepository repository;
    
    private final CaixaRepository caixaRepository;

    public List<FaturamentoDiarioResponse> faturamentoDiario(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findFaturamentoDiario(dataInicio, dataFim);
    }
    
    public List<FaturamentoMensalResponse> faturamentoMensal(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findFaturamentoMensal(dataInicio, dataFim);
    }
    
    public DashboardResumoResponse resumoDashboard() {
        var resumoDia = repository.findResumoDia();

        double faturamento = resumoDia.getTotalFaturamento() != null ? resumoDia.getTotalFaturamento() : 0;
        long totalVendas   = resumoDia.getTotalVendas()      != null ? resumoDia.getTotalVendas()      : 0;
        double ticketMedio = totalVendas > 0 ? faturamento / totalVendas : 0;

        var caixas = caixaRepository.findCaixasAbertosHoje().stream()
            .map(c -> DashboardResumoResponse.CaixaAbertoInfo.builder()
                .id(c.getId())
                .nome(c.getNome())
                .valorInicial(c.getValorInicial())
                .totalVendidoDinheiro(c.getTotalVendidoDinheiro())
                .totalGastoDinheiro(c.getTotalGastoDinheiro())
                .saldoEsperado(c.getSaldoEsperado())
                .build())
            .toList();

        return DashboardResumoResponse.builder()
            .faturamentoHoje(faturamento)
            .totalVendasHoje(totalVendas)
            .ticketMedioHoje(ticketMedio)
            .caixasAbertos(caixas)
            .build();
    }
}