package com.loja.autos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.loja.autos.dto.response.FaturamentoDiarioResponse;
import com.loja.autos.dto.response.FaturamentoMensalResponse;
import com.loja.autos.repository.RelatorioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelatorioServiceImpl {

    private final RelatorioRepository repository;

    public List<FaturamentoDiarioResponse> faturamentoDiario(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findFaturamentoDiario(dataInicio, dataFim);
    }
    
    public List<FaturamentoMensalResponse> faturamentoMensal(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findFaturamentoMensal(dataInicio, dataFim);
    }
}