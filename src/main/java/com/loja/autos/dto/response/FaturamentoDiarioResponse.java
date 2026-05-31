package com.loja.autos.dto.response;

import java.time.LocalDate;

public interface FaturamentoDiarioResponse {
	LocalDate getData();
    Double getTotalValor();
    Long getTotalVendas();
}
