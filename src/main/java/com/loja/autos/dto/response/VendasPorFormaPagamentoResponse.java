package com.loja.autos.dto.response;

public interface VendasPorFormaPagamentoResponse {
    String getFormaPagamento();
    Long getTotalTransacoes();
    Double getTotalValor();
}
