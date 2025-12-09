package com.loja.autos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.autos.entity.PagamentoVenda;

public interface PagamentoVendaRepository extends JpaRepository<PagamentoVenda, UUID> {

}
