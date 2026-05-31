package com.loja.autos.service;

import com.loja.autos.dto.response.FaturamentoDiarioResponse;
import com.loja.autos.repository.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioServiceImpl {

    private final RelatorioRepository repository;

    public List<FaturamentoDiarioResponse> faturamentoDiario(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findFaturamentoDiario(dataInicio, dataFim);
    }
}