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
public class VendaDetalheResponse {
    private String id;
    private String dataHora;
    private String nomeCaixa;
    private String nomeCliente;
    private String statusVenda;
    private String observacoes;
    private Double totalVenda;
    private List<ItemVendaInfo> itens;
    private List<PagamentoVendaInfo> pagamentos;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemVendaInfo {
        private String nomeProduto;
        private String tipoUnidade;
        private Double pesoEmKg;
        private Integer quantidadeUnidade;
        private Double valorUnitario;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PagamentoVendaInfo {
        private String formaPagamento;
        private Double valorPago;
    }
}