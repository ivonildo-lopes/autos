package com.loja.autos.controllers;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.ResponseDto;
import com.loja.autos.service.RelatorioServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioServiceImpl service;

    @GetMapping("/faturamento-diario")
    public ResponseEntity<?> faturamentoDiario(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.fromData(
                service.faturamentoDiario(dataInicio, dataFim),
                HttpStatus.OK,
                "Faturamento diário"
            ));
    }
    
    @GetMapping("/faturamento-mensal")
    public ResponseEntity<?> faturamentoMensal(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.fromData(
                service.faturamentoMensal(dataInicio, dataFim),
                HttpStatus.OK,
                "Faturamento mensal"
            ));
    }
    
    
    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.fromData(
                service.resumoDashboard(),
                HttpStatus.OK,
                "Resumo do dashboard"
            ));
    }
    
    @GetMapping("/historico-vendas")
    public ResponseEntity<?> historicoVendas(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.fromData(
                service.historicoVendas(dataInicio, dataFim),
                HttpStatus.OK,
                "Histórico de vendas"
            ));
    }

    @GetMapping("/historico-vendas/{id}")
    public ResponseEntity<?> detalheVenda(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.fromData(
                service.detalheVenda(id),
                HttpStatus.OK,
                "Detalhe da venda"
            ));
    }
}