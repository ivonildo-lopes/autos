package com.loja.autos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResumoResponse {
    private Double faturamentoHoje;
    private Long totalVendasHoje;
    private Double ticketMedioHoje;
    private List<CaixaAbertoInfo> caixasAbertos;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CaixaAbertoInfo {
        private String id;
        private String nome;
        private Double valorInicial;
        private Double totalVendidoDinheiro;
        private Double totalGastoDinheiro;
        private Double saldoEsperado;
    }
}