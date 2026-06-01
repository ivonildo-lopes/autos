package com.loja.autos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.loja.autos.dto.response.DashboardResumoResponse;
import com.loja.autos.dto.response.FaturamentoDiarioResponse;
import com.loja.autos.dto.response.FaturamentoMensalResponse;
import com.loja.autos.dto.response.VendaDetalheResponse;
import com.loja.autos.dto.response.VendaResumoResponse;
import com.loja.autos.repository.CaixaRepository;
import com.loja.autos.repository.RelatorioRepository;
import com.loja.autos.repository.VendaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelatorioServiceImpl {

    private final RelatorioRepository repository;
    
    private final CaixaRepository caixaRepository;
    
    private final VendaRepository vendaRepository;

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
    
    public List<VendaResumoResponse> historicoVendas(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findHistoricoVendas(dataInicio, dataFim).stream()
            .map(v -> VendaResumoResponse.builder()
                .id(v.getId())
                .dataHora(v.getDataHora())
                .nomeCaixa(v.getNomeCaixa())
                .statusVenda(v.getStatusVenda())
                .totalVenda(v.getTotalVenda())
                .formasPagamento(v.getFormasPagamento() != null
                    ? List.of(v.getFormasPagamento().split(", "))
                    : List.of())
                .build())
            .toList();
    }

    public VendaDetalheResponse detalheVenda(UUID id) {
        var venda = vendaRepository.findDetalheById(id);

        var itens = vendaRepository.findItensByVendaId(id).stream()
            .map(i -> VendaDetalheResponse.ItemVendaInfo.builder()
                .nomeProduto(i.getNomeProduto())
                .tipoUnidade(i.getTipoUnidade())
                .pesoEmKg(i.getPesoEmKg())
                .quantidadeUnidade(i.getQuantidadeUnidade())
                .valorUnitario(i.getValorUnitario())
                .build())
            .toList();

        var pagamentos = vendaRepository.findPagamentosByVendaId(id).stream()
            .map(p -> VendaDetalheResponse.PagamentoVendaInfo.builder()
                .formaPagamento(p.getFormaPagamento())
                .valorPago(p.getValorPago())
                .build())
            .toList();

        double total = pagamentos.stream()
            .mapToDouble(VendaDetalheResponse.PagamentoVendaInfo::getValorPago)
            .sum();

        return VendaDetalheResponse.builder()
            .id(venda.getId())
            .dataHora(venda.getDataHora())
            .nomeCaixa(venda.getNomeCaixa())
            .nomeCliente(venda.getNomeCliente())
            .statusVenda(venda.getStatusVenda())
            .observacoes(venda.getObservacoes())
            .totalVenda(total)
            .itens(itens)
            .pagamentos(pagamentos)
            .build();
    }
}