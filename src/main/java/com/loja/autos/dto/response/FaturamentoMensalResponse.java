package com.loja.autos.dto.response;

public interface FaturamentoMensalResponse {
    Integer getAno();
    Integer getMes();
    Double getTotalValor();
    Long getTotalVendas();
}